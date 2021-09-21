package com.group1.sports_rental.CustomerPage;

import java.util.ArrayList;
import java.util.List;

public class CustomerPageService implements ICustomerPageService
{

    static CustomerPageService instance = null;

    public static CustomerPageService instance()
    {
        if (instance == null)
        {
            instance = new CustomerPageService();
        }
        return instance;
    }

    public List<RentalHistory> getCustomerRentalHistory(Long userId, ICustomerPageDao customerPageDao)
    {
        List<RentalHistory> rentalHistoryList = new ArrayList<>();
        try
        {
            rentalHistoryList = customerPageDao.getUserRentalHistory(userId);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return rentalHistoryList;
    }

}
