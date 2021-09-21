package com.group1.sports_rental.RentTest;

import com.group1.sports_rental.Rental.IRentService;
import com.group1.sports_rental.Rental.RentService;
import com.group1.sports_rental.Rental.Rental;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Date;

public class RentTest
{

    @Test
    public void calculateDaysTest()
    {
        Rental rental = Rental.instance();
        Date startDate = Date.valueOf("2021-05-06");
        Date endDate = Date.valueOf("2021-05-09");
        Assertions.assertEquals(3,rental.calculateDays(startDate,endDate));
    }

    @Test
    public void calculateDaysErrorTest()
    {
        Rental rental = Rental.instance();
        Date startDate = Date.valueOf("2021-05-06");
        Date endDate = Date.valueOf("2021-05-06");
        Assertions.assertEquals(0,rental.calculateDays(startDate,endDate));
    }

    @Test
    public void checkAvailabilityTest()
    {
        RentDaoMock rentDaoMock = new RentDaoMock();
        IRentService rentService = RentService.instance();
        Date startDate = Date.valueOf("2021-05-19");
        Date endDate = Date.valueOf("2021-05-20");
        String rentedItemId ="gr_1000";
        Assertions.assertEquals(true,rentService.checkAvailability(startDate,endDate,rentedItemId,rentDaoMock));
    }

    @Test
    public void checkAvailabilityErrorTest()
    {
        RentDaoMock rentDaoMock = new RentDaoMock();
        IRentService rentService = RentService.instance();
        Date startDate = Date.valueOf("2021-03-23");
        Date endDate = Date.valueOf("2021-03-24");
        String rentedItemId ="pr_9000";
        Assertions.assertEquals(false,rentService.checkAvailability(startDate,endDate,rentedItemId,rentDaoMock));
    }

    @Test
    public void checkCreditsTest()
    {
        RentDaoMock rentDaoMock = new RentDaoMock();
        IRentService rentService = RentService.instance();
        long userId = 4;
        Assertions.assertEquals(true,rentService.checkCredits(userId,rentDaoMock));
    }

    @Test
    public void checkCreditsErrorTest()
    {
        RentDaoMock rentDaoMock = new RentDaoMock();
        IRentService rentService = RentService.instance();
        long userId = 5;
        Assertions.assertEquals(false,rentService.checkCredits(userId,rentDaoMock));
    }

    @Test
    public void insertRentalDetailsProductTest()
    {
        try {
            RentDaoMock rentDaoMock = new RentDaoMock();
            IRentService rentService = RentService.instance();
            rentService.insertRentalDetails(Date.valueOf("2021-05-07"),Date.valueOf("2021-05-09"),"pr_9000",4L, rentDaoMock);
        }
        catch (Exception e){
            Assertions.fail("Rent Insertion failed due to "+e.getMessage() );
        }
    }

    @Test
    public void insertRentalDetailsGroundTest()
    {
        try {
            RentDaoMock rentDaoMock = new RentDaoMock();
            IRentService rentService = RentService.instance();
            rentService.insertRentalDetails(Date.valueOf("2021-05-07"),Date.valueOf("2021-05-09"),"gr_1000",4L, rentDaoMock);
        }
        catch (Exception e){
            Assertions.fail("Rent Insertion failed due to "+e.getMessage() );
        }
    }
}
