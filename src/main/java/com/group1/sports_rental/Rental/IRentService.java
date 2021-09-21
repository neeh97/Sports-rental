package com.group1.sports_rental.Rental;

import java.sql.Date;

public interface IRentService
{

   Boolean checkAvailability(Date startDate, Date endDate, String rentedItemId, RentDao rentDao);
   Double calculateRent(String rentedItemId,Date startDate,Date endDate, RentDao rentDao);
   Boolean checkCredits(Long userId,RentDao rentDao);
   void insertRentalDetails(Date startDate, Date endDate,String rentedItemId,Long userId,RentDao rentDao) ;

}
