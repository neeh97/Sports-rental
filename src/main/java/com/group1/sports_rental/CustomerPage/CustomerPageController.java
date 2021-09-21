package com.group1.sports_rental.CustomerPage;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisementDao;
import com.group1.sports_rental.Advertisement.EventAdvertisement.IEventAdvertisementDao;
import com.group1.sports_rental.Tickets.EventTicketDao;
import com.group1.sports_rental.Tickets.IEventTicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CustomerPageController
{

    @Autowired
    CustomerPageDao customerPageDao = new CustomerPageDao();
    ICustomerPageService customerPageService = CustomerPageService.instance();
    ICustomerCreditService customerCreditService = CustomerCreditService.instance();
    ICustomerTicketService customerTicketService = CustomerTicketService.instance();
    IEventTicketDao eventTicketUserDao = new EventTicketDao();
    IEventAdvertisementDao eventAdvertisementDao = EventAdvertisementDao.instance();

    private final static String topUpURI = "/payment/credits/add/";

    @RequestMapping("/profile")
    public String profilePage(Model model, HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession == null || httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        Long userId = (Long) httpSession.getAttribute("userId");
        try
        {
            List<RentalHistory> rentalHistoryList = customerPageService.getCustomerRentalHistory(userId, customerPageDao);
            model.addAttribute("rentals", rentalHistoryList);
            CustomerCredit customerCredit = customerCreditService.getCreditsOfUser(userId, customerPageDao);
            if (customerCredit == null)
            {
                model.addAttribute("credits", "0");
            }
            else
            {
                model.addAttribute("credits", customerCredit.getCredits());
            }
            model.addAttribute("tickets", customerTicketService.fetchCustomerActiveTickets(userId, eventTicketUserDao, eventAdvertisementDao));
        } catch (Exception exception)
        {
            return "errorPage";
        }
        model.addAttribute("emptyCreditAmount", false);
        model.addAttribute("creditTopup", new CreditTopup());
        return "profile";
    }

    @PostMapping(value = "/profile/topUp")
    public String topUpCredits(@ModelAttribute("creditTopup") CreditTopup creditTopup, Model model, HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession == null || httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        Long userId = (Long) httpSession.getAttribute("userId");
        if (creditTopup.validateCreditAmount())
        {
            model.addAttribute("emptyCreditAmount", true);
        }
        else
        {
            String requestUrl = httpServletRequest.getRequestURL().substring(0, httpServletRequest.getRequestURL().indexOf(httpServletRequest.getRequestURI()));
            creditTopup.topUpCredits(userId, customerPageDao);
            return "redirect:"+requestUrl+topUpURI+userId.toString()+"/"+creditTopup.getCreditAmount();
        }
        model.addAttribute("tickets", customerTicketService.fetchCustomerActiveTickets(userId, eventTicketUserDao, eventAdvertisementDao));
        return "profile";
    }

    @RequestMapping(value = "/profile/refund")
    public String refundCredits(Model model, HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession == null || httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        Long userId = (Long) httpSession.getAttribute("userId");
        String requestUrl = httpServletRequest.getRequestURL().substring(0, httpServletRequest.getRequestURL().indexOf(httpServletRequest.getRequestURI()));
        customerCreditService.refundCredits(userId, requestUrl, customerPageDao);
        model.addAttribute("status", "Refunded");
        return "stripeCheckout";
    }
}
