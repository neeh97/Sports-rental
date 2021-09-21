package com.group1.sports_rental.Advertisement.EventAdvertisement;

import com.group1.sports_rental.Advertisement.Advertisement;
import java.sql.Date;
import java.sql.Time;

public class EventAdvertisement extends Advertisement
{
    private String eventId;
    private Integer ticketsBooked;
    private Integer totalTickets = 0;
    private Integer ticketsLeft;
    private String eventName;
    private String eventAddress;
    private String eventCity;
    private String eventState;
    private float ticketPrice;
    private float ticketTotalPrice;
    private Long userId;
    private Date eventDate;
    private Time startTime;
    private Time endTime;
    private String paymentStatus = "false";
    private Integer ticketsBookedByUser = 0;
    private final IEventAdvertisementService eventAdvertisementService;

    public Integer getTicketsBooked()
    {
        return ticketsBooked;
    }

    public void setTicketsBooked(Integer ticketsBooked)
    {
        this.ticketsBooked = ticketsBooked;
    }

    public EventAdvertisement()
    {
        eventAdvertisementService = new EventAdvertisementService();
    }

    public String getEventCity()
    {
        return eventCity;
    }

    public String getPaymentStatus()
    {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    public void setEventCity(String eventCity)
    {
        this.eventCity = eventCity;
    }

    public Integer getTicketsLeft()
    {
        return ticketsLeft;
    }

    public Integer getTicketsBookedByUser()
    {
        return ticketsBookedByUser;
    }

    public Float getTicketTotalPrice()
    {
        return ticketTotalPrice;
    }

    public void setTicketTotalPrice(float ticketTotalPrice)
    {
        this.ticketTotalPrice = ticketTotalPrice;
    }

    public void setTicketsBookedByUser(Integer ticketsBookedByUser)
    {
        this.ticketsBookedByUser = ticketsBookedByUser;
    }

    public Time getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Time startTime)
    {
        this.startTime = startTime;
    }

    public Time getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Time endTime)
    {
        this.endTime = endTime;
    }

    public void setTicketsLeft(Integer ticketsLeft)
    {
        this.ticketsLeft = ticketsLeft;
    }

    public String getEventName()
    {
        return eventName;
    }

    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    public String getEventAddress()
    {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress)
    {
        this.eventAddress = eventAddress;
    }

    public String getEventState()
    {
        return eventState;
    }

    public void setEventState(String eventState)
    {
        this.eventState = eventState;
    }

    public Float getTicketPrice()
    {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice)
    {
        this.ticketPrice = ticketPrice;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getEventId()
    {
        return eventId;
    }

    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }

    public Integer getTotalTickets()
    {
        return totalTickets;
    }

    public void setTotalTickets(Integer totalTickets)
    {
        this.totalTickets = totalTickets;
    }

    public Date getEventDate()
    {
        return eventDate;
    }

    public void setEventDate(Date eventDate)
    {
        this.eventDate = eventDate;
    }

    public Boolean validateTotalTickets()
    {
        if (this.totalTickets <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean validateTicketPrice()
    {
        if (this.ticketPrice < 0 || this.ticketPrice == 0.0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean validateCity(IEventAdvertisementDao eventAdvertisementDao)
    {
        String eventCity = eventAdvertisementService.fetchCityName(this.eventState,this.eventCity,eventAdvertisementDao);
        if (eventCity.isEmpty())
        {
            return false;
        }
        return true;
    }

    public Integer validateEventDate()
    {
        Long milliseconds=System.currentTimeMillis();
        java.sql.Date currentDate=new java.sql.Date(milliseconds);
        return this.eventDate.compareTo(currentDate);
    }

    public Boolean validateEventName()
    {
        if (this.eventName == null || this.eventName.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean validateEventAddress()
    {
        if (this.eventAddress == null || this.eventAddress.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Integer validateEventTimeRange()
    {
        return this.startTime.compareTo(endTime);
    }
}
