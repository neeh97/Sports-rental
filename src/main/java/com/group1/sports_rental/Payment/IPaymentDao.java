package com.group1.sports_rental.Payment;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

public interface IPaymentDao
{
    Integer fetchRentalId(ProductAdvertisement productAdvertisement) throws ParseException ;
    Payment displayRentalValues(Integer rentalId, ProductAdvertisement productAdvertisement);
    void insertPaymentData(HashMap<String,Object> transactionDetails, Payment payment);
    void updateRentalStatus(Integer rentalId);
    void updatePaymentCompleteStatus(Payment payment);
    String fetchTransaction(String paymentId);
    void updateRefundStatus(HashMap<String, Object> refundMap,String paymentId);
    void updateTicketPaymentStatus(String ticketId,String paymentId) throws SQLException;
    void updateTicketRefundStatus(String ticketId,String paymentId) throws SQLException;
    Integer fetchGroundRentalId(GroundAdvertisement groundAdvertisement) throws ParseException;
    Payment displayGroundRentalData(Integer rentalId,GroundAdvertisement groundAdvertisement);
}
