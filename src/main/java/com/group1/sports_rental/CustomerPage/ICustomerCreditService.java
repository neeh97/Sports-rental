package com.group1.sports_rental.CustomerPage;

public interface ICustomerCreditService
{

    CustomerCredit getCreditsOfUser(Long userId, ICustomerPageDao customerPageDao);
    void refundCredits(Long userId, String urlPrefix, ICustomerPageDao customerPageDao);
}
