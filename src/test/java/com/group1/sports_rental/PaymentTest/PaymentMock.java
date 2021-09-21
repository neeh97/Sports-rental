package com.group1.sports_rental.PaymentTest;

import java.util.HashMap;

public class PaymentMock
{
    public HashMap<String, Object> chargeRentalAmountMock(String token, Float amount)
    {
        HashMap<String, Object> transactionParams = new HashMap<>();
        transactionParams.put("amount", Math.round(amount));
        transactionParams.put("source", token);
        transactionParams.put("currency", "INR");
        transactionParams.put("transactionId","ch_12345678abcd");
        transactionParams.put("paymentStatus","Payment complete");
        transactionParams.put("paymentMode","card");
        transactionParams.put("email","root@gmail.com");
        return transactionParams;
    }

    public HashMap<String, Object> chargeRentalAmountMockFailure(String token, Float amount)
    {
        HashMap<String, Object> transactionParams = new HashMap<>();
        transactionParams.put("amount", Math.round(amount));
        transactionParams.put("source", token);
        transactionParams.put("currency", "INR");
        transactionParams.put("paymentStatus","card_declined");
        transactionParams.put("failureMessage","Your card has insufficient funds");
        return transactionParams;
    }

    public HashMap<String, Object> refundRentalAmountMock(String transactionId)
    {
        HashMap<String, Object> refundMap = new HashMap<>();
        refundMap.put("charge", transactionId);
        refundMap.put("amount",120);
        refundMap.put("currency","INR");
        refundMap.put("refundTransactionId","rf_12345");
        refundMap.put("paymentStatus","refund");
        return refundMap;
    }

    public HashMap<String, Object> refundRentalAmountFailureMock(String transactionId)
    {
        HashMap<String, Object> refundMap = new HashMap<>();
        refundMap.put("charge", transactionId);
        refundMap.put("paymentStatus","charge_refunded");
        refundMap.put("failureMessage","Your charge has been refunded already.");
        return refundMap;
    }

}
