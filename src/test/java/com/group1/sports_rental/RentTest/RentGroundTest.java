package com.group1.sports_rental.RentTest;

import com.group1.sports_rental.Rental.IRentService;
import com.group1.sports_rental.Rental.RentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Date;

public class RentGroundTest
{

    @Test
    public void calculateRentGroundTest()
    {
        RentDaoMock rentDaoMock = new RentDaoMock();
        IRentService rentService = RentService.instance();
        Date startDate = Date.valueOf("2021-05-06");
        Date endDate = Date.valueOf("2021-05-09");
        String rentedItemId = "gr_1000";
        Assertions.assertEquals(1200,rentService.calculateRent(rentedItemId,startDate,endDate,rentDaoMock));
    }

    @Test
    public void calculateRentProductGroundTest()
    {
        RentDaoMock rentDaoMock = new RentDaoMock();
        IRentService rentService = RentService.instance();
        Date startDate = Date.valueOf("2021-05-07");
        Date endDate = Date.valueOf("2021-05-07");
        String rentedItemId = "gr_1000";
        Assertions.assertEquals(0, rentService.calculateRent(rentedItemId, startDate, endDate, rentDaoMock));
    }
}
