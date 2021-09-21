package com.group1.sports_rental.Payment;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

public class PaymentService implements IPaymentService
{

    public Integer fetchRentalId(ProductAdvertisement productAdvertisement, IPaymentDao paymentDao) throws ParseException
    {
        return paymentDao.fetchRentalId(productAdvertisement);
    }

    public Payment displayRentalData(Integer rentalId, IPaymentDao paymentDao, ProductAdvertisement productAdvertisement)
    {
        return paymentDao.displayRentalValues(rentalId,productAdvertisement);
    }

    public void insertPaymentData(HashMap<String, Object> transactionDetails, Payment payment,IPaymentDao paymentDao)
    {
        paymentDao.insertPaymentData(transactionDetails,payment);
    }

    public void updateRentalStatus(Integer rentalId, IPaymentDao paymentDao)
    {
        paymentDao.updateRentalStatus(rentalId);
    }

    public void updatePaymentCompleteStatus(Payment payment,IPaymentDao paymentDao)
    {
        paymentDao.updatePaymentCompleteStatus(payment);
    }

    public String fetchTransaction(String paymentId,IPaymentDao paymentDao)
    {
        return paymentDao.fetchTransaction(paymentId);
    }

    public void updateRefundStatus(HashMap<String, Object> refundMap, String paymentId,IPaymentDao paymentDao)
    {
        paymentDao.updateRefundStatus(refundMap,paymentId);
    }

    public void updateTicketPaymentStatus(String ticketId,String paymentId,IPaymentDao paymentDao) throws SQLException
    {
        paymentDao.updateTicketPaymentStatus(ticketId,paymentId);
    }

    public void updateTicketRefundStatus(String ticketId, String paymentId,IPaymentDao paymentDao) throws SQLException
    {
        paymentDao.updateTicketRefundStatus(ticketId,paymentId);
    }

    public Integer fetchGroundRentalId(GroundAdvertisement groundAdvertisement, IPaymentDao paymentDao) throws ParseException
    {
       return paymentDao.fetchGroundRentalId(groundAdvertisement);
    }

    public Payment displayGroundRentalData(Integer rentalId, IPaymentDao paymentDao, GroundAdvertisement groundAdvertisement)
    {
        return paymentDao.displayGroundRentalData(rentalId,groundAdvertisement);
    }
}
