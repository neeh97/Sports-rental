package com.group1.sports_rental.PaymentTest;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import com.group1.sports_rental.Payment.IPaymentDao;
import com.group1.sports_rental.Payment.Payment;
import java.sql.*;
import java.text.ParseException;
import java.util.HashMap;

public class PaymentDaoMock implements IPaymentDao
{
    public Integer fetchRentalId(ProductAdvertisement productAdvertisement) throws ParseException
    {
        int rentalId = 0;
        Date startDateFormatted=Date.valueOf("2021-04-02");
        Date endDateFormatted = Date.valueOf("2021-04-09");
        if (productAdvertisement.getStartDate().equals(startDateFormatted) &&
                productAdvertisement.getEndDate().equals(endDateFormatted) &&
                productAdvertisement.getId().equals("pr_12345"))
        {
            rentalId = 222;
        }
        return rentalId;
    }

    public Payment displayRentalValues(Integer rentalId, ProductAdvertisement productAdvertisement)
    {
        Date startDate=Date.valueOf("2021-05-04");
        Date endDate=Date.valueOf("2021-05-29");
        Payment payment = new Payment();
        payment.setRentalId(123);
        payment.setAmount(120.F);
        productAdvertisement.setProductName("Tennis ball");
        productAdvertisement.setProductDescription("Made of leather");
        productAdvertisement.setStartDate(startDate);
        productAdvertisement.setEndDate(endDate);
        payment.setRenterId(1);
        return payment;
    }

    public void insertPaymentData(HashMap<String, Object> transactionDetails, Payment payment)
    {
        String uniquePaymentId = "ababab123";
        Float amount = (Float) transactionDetails.get("amount");
        String currency = (String) transactionDetails.get("currency");
        String email = (String) transactionDetails.get("email");
        payment.setRenterId(11);
        transactionDetails.get("transactionId");
        payment.setUserId(3L);
    }

    public void updateRentalStatus(Integer rentalId)
    {
        String status = "";
        if (rentalId == 1234)
        {
            status = "true";
        }
    }

    public void updatePaymentCompleteStatus(Payment payment)
    {
        Payment.PaymentStatus payment_status;
        if (payment.getRentalId() == 0 && payment.getTicketId().isEmpty())
        {
            payment_status = Payment.PaymentStatus.topUp;
        }
        else if(payment.getTicketId().length() > 0)
        {
            updateTicketPaymentStatus(payment.getTicketId(), payment.getPaymentId());
        }
        updateRentalStatus(payment.getRentalId());
    }

    public String fetchTransaction(String paymentId)
    {
        String transactionId = "";
        if (paymentId.equals("abcde12345"))
            transactionId = "ch_2hdhhdnfejj33";
        return transactionId;
    }

    public void updateRefundStatus(HashMap<String, Object> refundMap, String paymentId)
    {
        String paymentStatus;
        String refundId;
        if (paymentId.equals("pr_12345"))
        {
            refundId = "ch_12345sncd";
            paymentStatus = "refund";
        }
    }

    public void updateTicketPaymentStatus(String ticketId,String paymentId)
    {
        String paymentStatus;
        if (ticketId.equals("aa123") && paymentId.equals("pr_aaaaa"))
        {
            paymentStatus = "complete";
        }
    }

    public void updateTicketRefundStatus(String ticketId, String paymentId)
    {
        String paymentStatus;
        if (ticketId.equals("aa123") && paymentId.equals("pr_aaaaa"))
        {
            paymentStatus = "refund";
        }
    }

    public Integer fetchGroundRentalId(GroundAdvertisement groundAdvertisement) throws ParseException
    {
        int rentalId = 0;
        Date startDateFormatted=Date.valueOf("2021-04-02");
        Date endDateFormatted = Date.valueOf("2021-04-09");
        if (groundAdvertisement.getStartDate().equals(startDateFormatted) &&
                groundAdvertisement.getEndDate().equals(endDateFormatted) &&
                groundAdvertisement.getId().equals("gr_12345"))
        {
            rentalId = 222;
        }
        return rentalId;
    }

    public Payment displayGroundRentalData(Integer rentalId, GroundAdvertisement groundAdvertisement)
    {
        Date startDate=Date.valueOf("2021-05-04");
        Date endDate=Date.valueOf("2021-05-29");
        Payment payment = new Payment();
        payment.setRentalId(123);
        payment.setAmount(120.F);
        groundAdvertisement.setGroundName("Cricket Ground");
        groundAdvertisement.setAddress("Lake street");
        groundAdvertisement.setStartDate(startDate);
        groundAdvertisement.setEndDate(endDate);
        payment.setRenterId(1);
        return payment;
    }

}
