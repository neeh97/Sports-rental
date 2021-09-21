package com.group1.sports_rental.CustomerPage;

import java.util.List;

public interface ICustomerPageService
{

    List<RentalHistory> getCustomerRentalHistory(Long userId, ICustomerPageDao customerPageDao);
}
