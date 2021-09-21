package com.group1.sports_rental.Rental;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class Rental
{
    private Integer rentalId;
    private Date startDate;
    private Date endDate;
    private Boolean rentalStatus;
    private Double totalCost;
    private String rentedItemId;
    private Long userId;

    static Rental instance = null;
    public static Rental instance()
    {
        if(instance == null)
        {
            instance = new Rental();
        }
        return instance;
    }

    public String getRentedItemId()
    {
        return rentedItemId;
    }

    public void setRentedItemId(String rentedItemId)
    {
        this.rentedItemId = rentedItemId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Integer getRentalId()
    {
        return rentalId;
    }

    public void setRentalId(Integer rentalId)
    {
        this.rentalId = rentalId;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Boolean getRentalStatus()
    {
        return rentalStatus;
    }

    public void setRentalStatus(Boolean rentalStatus)
    {
        this.rentalStatus = rentalStatus;
    }

    public Double getTotalCost()
    {
        return totalCost;
    }

    public void setTotalCost(Double totalCost)
    {
        this.totalCost = totalCost;
    }

    public Long calculateDays(Date startDate, Date endDate)
    {
        Long numberOfDays = 0L;
        Date today = new Date(System.currentTimeMillis());
        if(startDate.compareTo(endDate)==0 || today.compareTo(startDate)>0 || startDate.compareTo(endDate)>0)
        {
            return numberOfDays;
        }
        else
        {
            // https://www.baeldung.com/java-date-difference
            Long differenceInTime = Math.abs(endDate.getTime() - startDate.getTime());
            numberOfDays = TimeUnit.DAYS.convert(differenceInTime, TimeUnit.MILLISECONDS);
            return numberOfDays;
        }
    }
}
