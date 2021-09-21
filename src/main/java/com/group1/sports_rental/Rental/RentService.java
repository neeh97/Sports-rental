package com.group1.sports_rental.Rental;

import java.sql.Date;

public class RentService implements IRentService
{
    static RentService instance = null;

    public static RentService instance()
    {
        if(instance == null)
        {
            instance = new RentService();
        }
        return instance;
    }

    public Boolean checkAvailability(Date startDate, Date endDate, String rentedItemId, RentDao rentDao)
    {
        return rentDao.checkAvailability(startDate,endDate,rentedItemId);
    }

    public Double calculateRent(String rentedItemId,Date startDate,Date endDate, RentDao rentDao)
    {
        return rentDao.calculateRent(rentedItemId,startDate,endDate);
    }

    public Boolean checkCredits(Long userId,RentDao rentDao)
    {
        return rentDao.checkCredits(userId);
    }

    public void insertRentalDetails(Date startDate, Date endDate,String rentedItemId,Long userId,RentDao rentDao)
    {
        rentDao.insertRentalDetails(startDate,endDate,rentedItemId,userId);
    }
}
