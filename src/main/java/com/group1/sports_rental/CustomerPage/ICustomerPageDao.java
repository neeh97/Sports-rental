package com.group1.sports_rental.CustomerPage;

import java.util.List;
import java.util.Map;

public interface ICustomerPageDao
{

    List<RentalHistory> getUserRentalHistory(Long userId);
    CustomerCredit getCreditsOfUser(Long userId);
    void topUpCredits(Long userId, String creditAmount);
    List<Map<String, Object>> refundCredits(Long userId);
    void removeCredits(Long userId, Float amount);
    Boolean callRefundApi(String apiUrl);
}
