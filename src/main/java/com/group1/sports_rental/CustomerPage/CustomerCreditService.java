package com.group1.sports_rental.CustomerPage;

import java.util.List;
import java.util.Map;

public class CustomerCreditService implements ICustomerCreditService
{

    private static String refundURL = "/payment/credits/refund/";

    static CustomerCreditService instance = null;

    public static CustomerCreditService instance()
    {
        if (instance == null)
        {
            instance = new CustomerCreditService();
        }
        return instance;
    }

    public CustomerCredit getCreditsOfUser(Long userId, ICustomerPageDao customerPageDao)
    {
        CustomerCredit customerCredit = null;
        try
        {
            customerCredit = customerPageDao.getCreditsOfUser(userId);
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return customerCredit;
    }

    public void refundCredits(Long userId, String urlPrefix, ICustomerPageDao customerPageDao)
    {
        List<Map<String, Object>> paymentList;
        try
        {
            paymentList = customerPageDao.refundCredits(userId);
            for (Map<String, Object> paymentMap : paymentList)
            {
                String paymentId = (String) paymentMap.get("paymentId");
                Float amount = (Float) paymentMap.get("amount");
                customerPageDao.removeCredits(userId, amount);
                String apiUrl = urlPrefix+refundURL+paymentId;
                customerPageDao.callRefundApi(apiUrl);
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
