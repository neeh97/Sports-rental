package com.group1.sports_rental.AdvertisementTest.EventAdvertisementTest;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisementService;
import com.group1.sports_rental.Advertisement.EventAdvertisement.IEventAdvertisementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.Time;

public class EventAdvertisementTest
{
    EventAdvertisement eventAdvertisement = new EventAdvertisement();

    public int validateEventDateMock()
    {
        Date currentDate = Date.valueOf("2021-03-29");
        Date eventDate =  eventAdvertisement.getEventDate();
        return eventDate.compareTo(currentDate);
    }

    @Test
    public void validateCityExistsTest()
    {
        EventAdvertisement eventAdvertisement = new EventAdvertisement();
        EventAdvertisementDaoMock eventAdvertisementDaoMock = new EventAdvertisementDaoMock();
        eventAdvertisement.setEventCity("Halifax");
        eventAdvertisement.setEventState("Nova Scotia");
        Assertions.assertEquals("Halifax",eventAdvertisementDaoMock.fetchCity(eventAdvertisement.getEventState(),eventAdvertisement.getEventCity()));
    }

    @Test
    public void validateCityNotExistsTest()
    {
        EventAdvertisement eventAdvertisement = new EventAdvertisement();
        EventAdvertisementDaoMock eventAdvertisementDaoMock = new EventAdvertisementDaoMock();
        eventAdvertisement.setEventCity("Toronto");
        eventAdvertisement.setEventState("Nova Scotia");
        Assertions.assertNull(eventAdvertisementDaoMock.fetchCity(eventAdvertisement.getEventState(), eventAdvertisement.getEventCity()));
    }

    @Test
    public void validateEventAddressTest()
    {
        EventAdvertisement eventAdvertisement = new EventAdvertisement();
        eventAdvertisement.setEventAddress("B044, Wellington street");
        Assertions.assertTrue(eventAdvertisement.validateEventAddress());
    }

    @Test
    public void validateEventAddressNotExistsTest()
    {
        EventAdvertisement eventAdvertisement = new EventAdvertisement();
        Assertions.assertFalse(eventAdvertisement.validateEventAddress());
    }

    @Test
    public void validateEventNameTest()
    {
        EventAdvertisement eventAdvertisement = new EventAdvertisement();
        eventAdvertisement.setEventName("SkateBaording event");
        Assertions.assertTrue(eventAdvertisement.validateEventName());
    }

    @Test
    public void validateEventNameNullTest()
    {
        EventAdvertisement eventAdvertisement = new EventAdvertisement();
        Assertions.assertFalse(eventAdvertisement.validateEventName());
    }

    @Test
    public void validateTotalTicketsTest()
    {
        EventAdvertisement eventAdvertisement = new EventAdvertisement();
        eventAdvertisement.setTotalTickets(200);
        Assertions.assertTrue(eventAdvertisement.validateTotalTickets());
    }

    @Test
    public void validateTotalTicketsNegativeTest()
    {
        EventAdvertisement eventAdvertisement = new EventAdvertisement();
        eventAdvertisement.setTotalTickets(-200);
        Assertions.assertFalse(eventAdvertisement.validateTotalTickets());
    }

    @Test
    public void validateTicketPriceTest()
    {
        EventAdvertisement eventAdvertisement = new EventAdvertisement();
        eventAdvertisement.setTicketPrice(2000f);
        Assertions.assertTrue(eventAdvertisement.validateTicketPrice());
    }

    @Test
    public void validateTicketPriceNegativeTest()
    {
        EventAdvertisement eventAdvertisement = new EventAdvertisement();
        eventAdvertisement.setTicketPrice(-0f);
        Assertions.assertFalse(eventAdvertisement.validateTicketPrice());
    }

    @Test
    public void validateEventDateTest()
    {
        Date eventDate = Date.valueOf("2021-03-31");
        eventAdvertisement.setEventDate(eventDate);
        Assertions.assertEquals(1,validateEventDateMock());
    }

    @Test
    public void validateEventDateInvalidTest()
    {
        Date eventDate = Date.valueOf("2021-03-27");
        eventAdvertisement.setEventDate(eventDate);
        Assertions.assertEquals(-1,validateEventDateMock());
    }

    @Test
    public void validateEventDateSameTest()
    {
        Date eventDate = Date.valueOf("2021-03-29");
        eventAdvertisement.setEventDate(eventDate);
        Assertions.assertEquals(0,validateEventDateMock());
    }

    @Test
    public void validateEventTimeRangeTest()
    {
        EventAdvertisement eventAdvertisement = new EventAdvertisement();
        Time startTime = Time.valueOf("09:00:00");
        Time endTime = Time.valueOf("11:00:00");
        eventAdvertisement.setStartTime(startTime);
        eventAdvertisement.setEndTime(endTime);
        Assertions.assertEquals(-1,eventAdvertisement.validateEventTimeRange());
    }

    @Test
    public void validateEventTimeRangeInvalidTest()
    {
        EventAdvertisement eventAdvertisement = new EventAdvertisement();
        Time startTime = Time.valueOf("11:00:00");
        Time endTime = Time.valueOf("09:00:00");
        eventAdvertisement.setStartTime(startTime);
        eventAdvertisement.setEndTime(endTime);
        Assertions.assertEquals(1,eventAdvertisement.validateEventTimeRange());
    }

    @Test
    public void validateEventTimeRangeSameTest()
    {
        EventAdvertisement eventAdvertisement = new EventAdvertisement();
        Time startTime = Time.valueOf("09:00:00");
        Time endTime = Time.valueOf("09:00:00");
        eventAdvertisement.setStartTime(startTime);
        eventAdvertisement.setEndTime(endTime);
        Assertions.assertEquals(0,eventAdvertisement.validateEventTimeRange());
    }

    @Test
    public void fetchCityNameTest()
    {
        EventAdvertisementDaoMock eventAdvertisementDaoMock = new EventAdvertisementDaoMock();
        IEventAdvertisementService eventAdvertisementService = new EventAdvertisementService();
        Assertions.assertEquals("halifax",eventAdvertisementService.fetchCityName("nova scotia","halifax",eventAdvertisementDaoMock));
    }

    @Test
    public void insertEventRecordTest()
    {
        AbstractFactory factory = new ConcreteFactory();
        EventAdvertisement eventAdvertisement  = factory.createEvent();
        EventAdvertisementDaoMock eventAdvertisementDaoMock = new EventAdvertisementDaoMock();
        IEventAdvertisementService eventAdvertisementService = new EventAdvertisementService();
        try
        {
            eventAdvertisementService.insertEventRecord(eventAdvertisement, eventAdvertisementDaoMock);
        }
        catch (Exception exception)
        {
            Assertions.fail("Event advertisement insertion failed"+exception.getMessage() );
        }
    }
}
