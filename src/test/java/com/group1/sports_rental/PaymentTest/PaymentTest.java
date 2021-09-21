package com.group1.sports_rental.PaymentTest;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import com.group1.sports_rental.Payment.IPaymentService;
import com.group1.sports_rental.Payment.Payment;
import com.group1.sports_rental.Payment.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;

public class PaymentTest
{
    @Test
    public void chargeRentalAmountMockTest()
    {
        PaymentMock paymentMock = new PaymentMock();
        Assertions.assertEquals("ch_12345678abcd",paymentMock.chargeRentalAmountMock("abababababababa", 120F).get("transactionId"));
    }

    @Test
    public  void chargeRentalAmountMockFailureTest()
    {
        PaymentMock paymentMock = new PaymentMock();
        Assertions.assertEquals("card_declined",paymentMock.chargeRentalAmountMockFailure("bbbbbbbbbbbbbbbbbb", 120F).get("paymentStatus"));

    }

    @Test
    public void refundRentalAmountMockTest()
    {
        PaymentMock paymentMock = new PaymentMock();
        Assertions.assertEquals("refund",paymentMock.refundRentalAmountMock("abababababababa").get("paymentStatus"));
    }

    @Test
    public  void refundRentalAmountMockFailureTest()
    {
        PaymentMock paymentMock = new PaymentMock();
        Assertions.assertEquals("charge_refunded",paymentMock.refundRentalAmountFailureMock("abababababababa").get("paymentStatus"));
    }

    @Test
    public void fetchRentalIdTest() throws ParseException {
        PaymentDaoMock paymentDaoMock = new PaymentDaoMock();
        IPaymentService paymentService = new PaymentService();
        Date startDateFormatted=Date.valueOf("2021-04-02");
        Date endDateFormatted = Date.valueOf("2021-04-09");
        String productId = "pr_12345";
        AbstractFactory factory = new ConcreteFactory();
        ProductAdvertisement productAdvertisement = factory.createProduct();
        productAdvertisement.setStartDate(startDateFormatted);
        productAdvertisement.setEndDate(endDateFormatted);
        productAdvertisement.setId(productId);
        Assertions.assertEquals(222,paymentService.fetchRentalId(productAdvertisement,paymentDaoMock));
    }

    @Test
    public void displayRentalDataTest()
    {
        AbstractFactory factory = new ConcreteFactory();
        ProductAdvertisement productAdvertisement = factory.createProduct();
        PaymentDaoMock paymentDaoMock = new PaymentDaoMock();
        IPaymentService paymentService = new PaymentService();
        Assertions.assertEquals(120.F,paymentService.displayRentalData(123,paymentDaoMock,productAdvertisement).getAmount());
    }

    @Test
    public void insertPaymentDataTest()
    {
        Payment payment = new Payment();
        HashMap<String, Object> transactionDetails = new HashMap<>();
        PaymentDaoMock paymentDaoMock = new PaymentDaoMock();
        IPaymentService paymentService = new PaymentService();
        try
        {
            paymentService.insertPaymentData(transactionDetails,payment,paymentDaoMock);
        }
        catch (Exception exception)
        {
            Assertions.fail("Payment could not be inserted"+exception.getMessage());
        }
    }

    @Test
    public void updateRentalStatusTest()
    {
        PaymentDaoMock paymentDaoMock = new PaymentDaoMock();
        IPaymentService paymentService = new PaymentService();
        try
        {
            paymentService.updateRentalStatus(1234,paymentDaoMock);
        }
        catch (Exception exception)
        {
            Assertions.fail("Update rental status failed"+exception.getMessage());
        }
    }

    @Test
    public void updatePaymentCompleteStatusTest()
    {
        PaymentDaoMock paymentDaoMock = new PaymentDaoMock();
        IPaymentService paymentService = new PaymentService();
        Payment payment = new Payment();
        try
        {
            paymentService.updatePaymentCompleteStatus(payment,paymentDaoMock);
        }
        catch (Exception exception)
        {
            Assertions.fail("Update payment complete failed"+exception.getMessage());
        }
    }

    @Test
    public void fetchTransaction()
    {
        PaymentDaoMock paymentDaoMock = new PaymentDaoMock();
        IPaymentService paymentService = new PaymentService();
        String paymentId = "abcde12345";
        paymentService.fetchTransaction(paymentId,paymentDaoMock);
    }

    @Test
    public void updateRefundStatus()
    {
        PaymentDaoMock paymentDaoMock = new PaymentDaoMock();
        IPaymentService paymentService = new PaymentService();
        HashMap<String, Object> transactionDetails = new HashMap<>();
        String paymentId = "pr_12345";
        try
        {
            paymentService.updateRefundStatus(transactionDetails,paymentId,paymentDaoMock);
        }
        catch (Exception exception)
        {
            Assertions.fail("Update refund failed"+exception.getMessage());
        }
    }

    @Test
    public void updateTicketPaymentStatus()
    {
        PaymentDaoMock paymentDaoMock = new PaymentDaoMock();
        IPaymentService paymentService = new PaymentService();
        HashMap<String, Object> transactionDetails = new HashMap<>();
        String paymentId = "pr_12345";
        String ticketId = "aa123";
        try
        {
            paymentService.updateTicketPaymentStatus(ticketId,paymentId,paymentDaoMock);
        }
        catch (Exception exception)
        {
            Assertions.fail("Update ticket payment complete failed"+exception.getMessage());
        }
    }

    @Test
    public void updateTicketRefundStatus()
    {
        PaymentDaoMock paymentDaoMock = new PaymentDaoMock();
        IPaymentService paymentService = new PaymentService();
        HashMap<String, Object> transactionDetails = new HashMap<>();
        String paymentId = "pr_12345";
        String ticketId = "aa123";
        try
        {
            paymentService.updateTicketRefundStatus(ticketId,paymentId,paymentDaoMock);
        }
        catch (Exception exception)
        {
            Assertions.fail("Update ticket refund failed"+exception.getMessage());
        }
    }

    @Test
    public void fetchGroundRentalIdTest() throws ParseException
    {
        PaymentDaoMock paymentDaoMock = new PaymentDaoMock();
        IPaymentService paymentService = new PaymentService();
        Date startDateFormatted=Date.valueOf("2021-04-02");
        Date endDateFormatted = Date.valueOf("2021-04-09");
        String groundId = "gr_12345";
        AbstractFactory factory = new ConcreteFactory();
        GroundAdvertisement groundAdvertisement = factory.createGround();
        groundAdvertisement.setStartDate(startDateFormatted);
        groundAdvertisement.setEndDate(endDateFormatted);
        groundAdvertisement.setId(groundId);
        Assertions.assertEquals(222,paymentService.fetchGroundRentalId(groundAdvertisement,paymentDaoMock));
    }

    @Test
    public void displayGroundRentalDataTest()
    {
        AbstractFactory factory = new ConcreteFactory();
        GroundAdvertisement groundAdvertisement = factory.createGround();
        PaymentDaoMock paymentDaoMock = new PaymentDaoMock();
        IPaymentService paymentService = new PaymentService();
        Assertions.assertEquals(120.F,paymentService.displayGroundRentalData(123,paymentDaoMock,groundAdvertisement).getAmount());
    }
}

