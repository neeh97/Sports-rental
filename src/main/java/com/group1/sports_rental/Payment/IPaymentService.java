package com.group1.sports_rental.Payment;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

public interface IPaymentService
{
    Integer fetchRentalId(ProductAdvertisement productAdvertisement, IPaymentDao paymentDao) throws ParseException;
    Payment displayRentalData(Integer rentalId, IPaymentDao paymentDao,ProductAdvertisement productAdvertisement);
    void insertPaymentData(HashMap<String,Object> transactionDetails, Payment payment,IPaymentDao paymentDao);
    void updateRentalStatus(Integer rentalId,IPaymentDao paymentDao);
    void updatePaymentCompleteStatus(Payment payments,IPaymentDao paymentDao);
    String fetchTransaction(String paymentId,IPaymentDao paymentDao);
    void updateRefundStatus(HashMap<String, Object> refundMap,String paymentId,IPaymentDao paymentDao);
    void updateTicketPaymentStatus(String ticketId,String paymentId,IPaymentDao paymentDao) throws SQLException;
    void updateTicketRefundStatus(String ticketId,String paymentId,IPaymentDao paymentDao) throws SQLException;
    Integer fetchGroundRentalId(GroundAdvertisement groundAdvertisement, IPaymentDao paymentDao) throws ParseException;
    Payment displayGroundRentalData(Integer rentalId, IPaymentDao paymentDao,GroundAdvertisement groundAdvertisement);
}
