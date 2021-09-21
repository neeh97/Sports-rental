package com.group1.sports_rental.RentTest;

import com.group1.sports_rental.Rental.RentDao;
import com.group1.sports_rental.Rental.Rental;
import java.sql.Date;

public class RentDaoMock extends RentDao
{
    Rental rental = Rental.instance();

    public Double calculateRent(String rentedItemId, Date startDate, Date endDate)
    {
        double cost = 400;
        long days = rental.calculateDays(startDate,endDate);
        if(days>0)
        {
            return cost*days;
        }
        else
        {
            return 0d;
        }
    }

    public Boolean checkAvailability(Date startDate, Date endDate, String rentedItemId)
    {
        Date existedStartDate = Date.valueOf("2021-03-23");
        Date existedEndDate = Date.valueOf("2021-03-25");
        Boolean rentalStatus = true;
        Boolean isAvailable = true;
        if(rentalStatus)
        {
            if((startDate.after(existedStartDate) && startDate.before(existedEndDate)) || (endDate.after(existedStartDate) && endDate.before(existedEndDate)))
            {
                isAvailable = false;
            }
        }
        else
        {
            isAvailable = true;
        }
        return isAvailable;
    }

    public Boolean checkCredits(Long userId)
    {
        float credits = 500;
        float minCredits = 200;
        Boolean isSufficientCredits = false;
        if(userId == 4)
        {
            if (credits > minCredits)
            {
                isSufficientCredits= true;
            }
            else {
                isSufficientCredits= false;
            }
        }
        return isSufficientCredits;
    }

    public void insertRentalDetails(Date startDate, Date endDate,String rentedItemId,Long userId)
    {
        Rental rental = Rental.instance();
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        rental.setRentedItemId(rentedItemId);
        rental.setUserId(userId);
        rental.setRentalStatus(false);
        rental.setTotalCost(calculateRent(rentedItemId,startDate,endDate));
    }
}
