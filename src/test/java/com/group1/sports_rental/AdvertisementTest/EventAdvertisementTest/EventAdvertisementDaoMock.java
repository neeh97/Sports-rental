package com.group1.sports_rental.AdvertisementTest.EventAdvertisementTest;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import com.group1.sports_rental.Advertisement.EventAdvertisement.IEventAdvertisementDao;
import org.springframework.util.StringUtils;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventAdvertisementDaoMock implements IEventAdvertisementDao
{
    public String fetchCity(String eventState, String eventCity)
    {
        String validCity = null;
        String stateLower = eventState.toLowerCase();
        String cityLower = eventCity.toLowerCase();
        String citySpaceRemoved = cityLower.replace(" ","");
        if (citySpaceRemoved.equals("halifax") && stateLower.equals("nova scotia"))
        {
             validCity = eventCity;
        }
        return validCity;
    }

    public void insertEventRecord(EventAdvertisement eventAdvertisement)
    {
        Time startTime = Time.valueOf("09:00:00");
        Time endTime = Time.valueOf("10:00:00");
        Date eventDate=Date.valueOf("2021-04-04");
        eventAdvertisement.setEventId("ev_2weffrhyhjjj");
        eventAdvertisement.setTotalTickets(44);
        eventAdvertisement.setEventName("cricket stadium");
        eventAdvertisement.setEventAddress("street");
        eventAdvertisement.setEventCity("halifax");
        eventAdvertisement.setEventState("nova scotia");
        eventAdvertisement.setTicketPrice(40F);
        eventAdvertisement.setUserId(12L);
        eventAdvertisement.setPaymentStatus("false");
        eventAdvertisement.setEventDate(eventDate);
        eventAdvertisement.setStartTime(startTime);
        eventAdvertisement.setEndTime(endTime);
    }

    public List<EventAdvertisement> applyFilters(String city, String location, String minimumPrice, String maximumPrice)
    {
        List<EventAdvertisement> eventAdvertisementList = new ArrayList<>();
        if (StringUtils.hasText(location) && location.equals("Shiganshina"))
        {
            EventAdvertisement eventAdvertisement = new EventAdvertisement();
            eventAdvertisement.setEventName("Rumbling");
            eventAdvertisement.setEventId("125");
            eventAdvertisement.setEventAddress("Paradise Island");
            eventAdvertisement.setEventCity("Shiganshina");
            eventAdvertisement.setTotalTickets(50000);
            eventAdvertisement.setTicketPrice(200F);
            eventAdvertisementList.add(eventAdvertisement);
        }
        if (StringUtils.hasText(minimumPrice) && minimumPrice.equals("100"))
        {
            EventAdvertisement eventAdvertisement = new EventAdvertisement();
            eventAdvertisement.setEventId("123");
            eventAdvertisement.setEventName("Queen Concert");
            eventAdvertisement.setEventCity("Halifax");
            eventAdvertisement.setTicketPrice(100F);
            eventAdvertisement.setTotalTickets(10000);
            eventAdvertisementList.add(eventAdvertisement);
        }
        if (StringUtils.hasText(maximumPrice) && maximumPrice.equals("200"))
        {
            EventAdvertisement eventAdvertisement = new EventAdvertisement();
            eventAdvertisement.setEventId("126");
            eventAdvertisement.setEventName("Joy Division Concert");
            eventAdvertisement.setEventCity("Halifax");
            eventAdvertisement.setTicketPrice(200F);
            eventAdvertisement.setTotalTickets(10000);
            eventAdvertisementList.add(eventAdvertisement);
        }
        return eventAdvertisementList;
    }

    public List<EventAdvertisement> getDefaultEvents(String city)
    {
        List<EventAdvertisement> eventAdvertisementList = new ArrayList<>();
        if (city.equals("Scranton"))
        {
            EventAdvertisement eventAdvertisement = new EventAdvertisement();
            eventAdvertisement.setEventName("Roast of Michael Scott");
            eventAdvertisement.setEventId("123");
            eventAdvertisement.setEventCity("Scranton");
            eventAdvertisement.setEventAddress("Dunder Mifflin, Scranton");
            eventAdvertisement.setTotalTickets(100);
            eventAdvertisement.setTicketPrice(10F);
            eventAdvertisementList.add(eventAdvertisement);
        }
        return eventAdvertisementList;
    }

    public List<EventAdvertisement> searchEventName(String city, String searchText)
    {
        List<EventAdvertisement> eventAdvertisementList = new ArrayList<>();
        if (searchText.equals("Battle") && city.equals("Hornburg"))
        {
            EventAdvertisement eventAdvertisement = new EventAdvertisement();
            eventAdvertisement.setEventName("Battle of Helm's Deep");
            eventAdvertisement.setEventId("124");
            eventAdvertisement.setEventCity("Hornburg");
            eventAdvertisement.setEventAddress("Rohan");
            eventAdvertisement.setTotalTickets(10000);
            eventAdvertisement.setTicketPrice(10F);
            eventAdvertisementList.add(eventAdvertisement);
        }
        return eventAdvertisementList;
    }

    public EventAdvertisement findOne(String eventId)
    {
        EventAdvertisement eventAdvertisement = null;
        if (eventId.equals("event1")) {
            try
            {
                eventAdvertisement = new EventAdvertisement();
                eventAdvertisement.setEventId(eventId);
                eventAdvertisement.setEventName("Metallica Concert");
                eventAdvertisement.setEventCity("halifax");
                eventAdvertisement.setTicketPrice(100F);
                eventAdvertisement.setTotalTickets(10000);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = simpleDateFormat.parse("2021-04-30");
                eventAdvertisement.setEventDate(new Date(date.getTime()));
            } catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
        if (eventId.equals("event2")) {
            try
            {
                eventAdvertisement = new EventAdvertisement();
                eventAdvertisement.setEventId(eventId);
                eventAdvertisement.setEventName("Radiohead Concert");
                eventAdvertisement.setEventCity("halifax");
                eventAdvertisement.setTicketPrice(100F);
                eventAdvertisement.setTotalTickets(10000);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = simpleDateFormat.parse("2021-04-02");
                eventAdvertisement.setEventDate(new Date(date.getTime()));
            } catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
        return eventAdvertisement;
    }
}
