package com.group1.sports_rental.Payment;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import com.group1.sports_rental.Utils.ApplicationConfiguration;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

@Controller
public class PaymentController
{
    ApplicationConfiguration applicationConfiguration = ApplicationConfiguration.instance();
    private final String stripePublicKey = applicationConfiguration.getSTRIPE_PUBLICKEY();
    private IPaymentDao paymentDao = new PaymentDao();
    private Payment payment = new Payment();
    private AbstractFactory factory = new ConcreteFactory();
    private ProductAdvertisement productAdvertisement = factory.createProduct();
    private IPaymentService paymentService = new PaymentService();
    private GroundAdvertisement groundAdvertisement = factory.createGround();

    @RequestMapping(value = "/payment/{id}/{startDate}/{endDate}")
    public String paymentConfirm(Model model, HttpServletRequest httpServletRequest, @PathVariable("id") String id, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws ParseException
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession.getAttribute("login") == null)
        {
           return "home";
        }
        else
        {
            String productIdPrefix = "pr";
            if (id.startsWith(productIdPrefix))
            {
                productAdvertisement.setId(id);
                productAdvertisement.setStartDate(Date.valueOf(startDate));
                productAdvertisement.setEndDate(Date.valueOf(endDate));
                Integer rentalId = paymentService.fetchRentalId(productAdvertisement,paymentDao);
                Payment payments = paymentService.displayRentalData(rentalId,paymentDao,productAdvertisement);
                model.addAttribute("payment",payments);
                model.addAttribute("productAdvertisement",productAdvertisement);
                model.addAttribute("StripePublicKey",stripePublicKey);
                httpSession.setAttribute("amount",payments.getAmount());
                httpSession.setAttribute("payment",payments);
                return "payment";
            }
            else
            {
                groundAdvertisement.setId(id);
                groundAdvertisement.setStartDate(Date.valueOf(startDate));
                groundAdvertisement.setEndDate(Date.valueOf(endDate));
                Integer rentalId = paymentService.fetchGroundRentalId(groundAdvertisement,paymentDao);
                Payment payments = paymentService.displayGroundRentalData(rentalId,paymentDao,groundAdvertisement);
                model.addAttribute("payment",payments);
                model.addAttribute("groundAdvertisement",groundAdvertisement);
                model.addAttribute("StripePublicKey",stripePublicKey);
                httpSession.setAttribute("amount",payments.getAmount());
                httpSession.setAttribute("payment",payments);
                return "paymentGround";
            }
        }
    }

    @PostMapping(value = "/payment/checkout/charge")
    public String charge(Model model, HttpServletRequest httpServletRequest) throws StripeException
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            String token = httpServletRequest.getParameter("stripeToken");
            Float price = (Float) httpSession.getAttribute("amount");
            Payment payments = (Payment) httpSession.getAttribute("payment");
            HashMap<String, Object> transactionDetails = payments.chargeRentalAmount(token,price);
            if (transactionDetails.get("transactionId") == null)
            {
                model.addAttribute("failureMessage",transactionDetails.get("failureMessage"));
                paymentService.insertPaymentData(transactionDetails, payments,paymentDao);
                return "stripeFailed";
            }
            paymentService.insertPaymentData(transactionDetails, payments,paymentDao);
            paymentService.updatePaymentCompleteStatus(payments,paymentDao);
            return "stripeCheckout";
        }
    }

    @RequestMapping(value = "/payment/credits/add/{userId}/{amount}")
    public String addCredits(Model model, HttpServletRequest httpServletRequest, @PathVariable("userId") String userIdVar, @PathVariable("amount") String amountVar)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            Long userId = Long.parseLong(userIdVar);
            Float amount = Float.parseFloat(amountVar);
            payment.setUserId(userId);
            payment.setAmount(amount);
            model.addAttribute("payment",payment);
            model.addAttribute("StripePublicKey",stripePublicKey);
            httpSession.setAttribute("payment",payment);
            httpSession.setAttribute("amount",payment.getAmount());
            return "paymentCredit";
        }
    }

    @RequestMapping(value = "/payment/credits/refund/{paymentId}")
    public String refundCredits(@PathVariable String paymentId,HttpServletRequest httpServletRequest) throws StripeException
    {
        String transactionId = paymentService.fetchTransaction(paymentId,paymentDao);
        HashMap<String, Object> refundMap = payment.refundAmount(transactionId);
        if (refundMap.get("refundTransactionId") == null)
        {
            return "stripeFailed";
        }
        paymentService.updateRefundStatus(refundMap,paymentId,paymentDao);
        return "stripeCheckout";
    }

    @RequestMapping(value = "/payment/{ticketId}/{totalTicketPrice}")
    public String ticketPayment(Model model, HttpServletRequest httpServletRequest, @PathVariable("ticketId") String ticketId, @PathVariable("totalTicketPrice") String totalTicketPrice) throws ParseException
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            Long userId = (Long) httpSession.getAttribute("userId");
            payment.setAmount(Float.parseFloat(totalTicketPrice));
            payment.setUserId(userId);
            payment.setTicketId(ticketId);
            model.addAttribute("payment",payment);
            model.addAttribute("StripePublicKey",stripePublicKey);
            httpSession.setAttribute("amount",payment.getAmount());
            httpSession.setAttribute("payment",payment);
            return "paymentTicket";
        }
    }

    @RequestMapping(value = "/payment/refund/{ticketId}/{paymentId}")
    public String refundTicket(@PathVariable("paymentId") String paymentId, @PathVariable("ticketId") String ticketId,HttpServletRequest httpServletRequest) throws StripeException, SQLException {
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            String transactionId = paymentService.fetchTransaction(paymentId,paymentDao);
            HashMap<String, Object> refundMap = payment.refundAmount(transactionId);
            if (refundMap.get("refundTransactionId") == null)
            {
                return "stripeFailed";
            }
            paymentService.updateRefundStatus(refundMap,paymentId,paymentDao);
            paymentService.updateTicketRefundStatus(ticketId,paymentId,paymentDao);
            return "stripeCheckout";
        }
    }
}
