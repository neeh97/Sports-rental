package com.group1.sports_rental.CustomerPageTests;

import com.group1.sports_rental.CustomerPage.CustomerCredit;
import com.group1.sports_rental.CustomerPage.ICustomerPageDao;
import com.group1.sports_rental.CustomerPage.RentalHistory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerPageMock implements ICustomerPageDao {

    private Float credits;

    public List<RentalHistory> getUserRentalHistory(Long userId)
    {
        List<RentalHistory> rentalHistoryList = new ArrayList<>();
        if (userId == 1L)
        {
            RentalHistory rentalHistory = new RentalHistory();
            rentalHistory.setProductName("Yoga mat");
            rentalHistory.setAmount(1000F);
            rentalHistory.setStartDate(Date.valueOf("2021-03-21"));
            rentalHistory.setEndDate(Date.valueOf("2021-04-01"));
            rentalHistoryList.add(rentalHistory);
        }
        return rentalHistoryList;
    }

    public CustomerCredit getCreditsOfUser(Long userId)
    {
        CustomerCredit customerCredit = null;
        if (userId == 1L)
        {
            customerCredit = new CustomerCredit();
            customerCredit.setUserId(userId.toString());
            customerCredit.setCredits(100F);
        }
        return customerCredit;
    }

    public void topUpCredits(Long userId, String creditAmount)
    {
        if (userId == 1L || userId == 2L)
        {
            this.credits = Float.parseFloat(creditAmount);
        }

    }

    public List<Map<String, Object>> refundCredits(Long userId)
    {
        List<Map<String, Object>> paymentList = new ArrayList<>();
        if (userId == 1L)
        {
            Map<String, Object> paymentMap = new HashMap<>();
            paymentMap.put("paymentId", "abc");
            paymentMap.put("amount",100F);
            paymentList.add(paymentMap);
        }
        if (userId == 2L)
        {
            Map<String, Object> paymentMap = new HashMap<>();
            paymentMap.put("paymentId", "fakeId");
            paymentMap.put("amount",100F);
            paymentList.add(paymentMap);
        }
        return paymentList;
    }

    public void removeCredits(Long userId, Float amount)
    {
        this.credits -= amount;
    }

    public Boolean callRefundApi(String apiUrl)
    {
        return apiUrl.equals("api/payment/credits/refund/abc");
    }

    public Float getCredits() {
        return credits;
    }
}
