package com.group1.sports_rental.EventPageTest;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import com.group1.sports_rental.EventPage.IEventPageDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;

public class EventPageDaoMock implements IEventPageDao
{
    public EventAdvertisement displayEvent(String eventId)
    {
        AbstractFactory factory = new ConcreteFactory();
        EventAdvertisement eventAdvertisement  = factory.createEvent();
        Date eventDate = Date.valueOf("2021-03-29");
        Time startTime = Time.valueOf("09:00:00");
        Time endTime = Time.valueOf("10:00:00");
        eventAdvertisement.setEventId("ev_12fyhhb");
        eventAdvertisement.setEventName("Football event");
        eventAdvertisement.setEventAddress("1024,Wellington street");
        eventAdvertisement.setEventCity("Halifax");
        eventAdvertisement.setEventState("NovaScotia");
        eventAdvertisement.setTicketPrice(33F);
        eventAdvertisement.setTotalTickets(1000);
        eventAdvertisement.setTicketsBooked(0);
        eventAdvertisement.setTicketsLeft(100);
        eventAdvertisement.setEventDate(eventDate);
        eventAdvertisement.setStartTime(startTime);
        eventAdvertisement.setEndTime(endTime);
        return eventAdvertisement;
    }

    public InputStream getEventImage(String eventId)
    {
        File eventImage = new File("src/main/resources/test/yoga-mat-500x500.jpg");
        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(eventImage);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return inputStream;
    }
}
