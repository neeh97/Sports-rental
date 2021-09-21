package com.group1.sports_rental.EventTicketTest;

import com.group1.sports_rental.Tickets.IEventTicketDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventTicketDaoMock implements IEventTicketDao
{
    public Float fetchTicketPrice(String eventId)
    {
        float perTicketPrice = 0.0f;
        if (eventId.equals("ev_12345abcd"))
        {
            perTicketPrice = 176.0F;
        }
        return perTicketPrice;
    }

    public String insertTicketData(String eventId, Integer tickets, Float totalTicketPrice, Long userId)
    {
        String ticketId = "ok1234";
        int ticketsMock = tickets;
        float totalTicketPriceMock = 55;
        long userIdMock = 1;
        String eventIdMock = "ev_abcd1234";
        String paymentStatusMock = "pending";
        return ticketId;
    }

    public Integer fetchTicketCount(String eventId)
    {
        int ticketCount = 0;
        if (eventId.equals("ev_12345abcd"))
        {
            ticketCount = 5;
        }
        return  ticketCount;
    }

    public void updateTicketCount(String eventId, Integer ticketsLeft) throws SQLException
    {
        int ticketLeftCount = 0;
        if (eventId.equals("ev_12345abcd"))
        {
            ticketLeftCount = 400;
        }
    }

    public List<Map<String, Object>> fetchEventTicketsOfUser(Long userId)
    {
        List<Map<String, Object>> ticketList = new ArrayList<>();
        if (userId == 1L)
        {
            Map<String, Object> ticketMap = new HashMap<>();
            ticketMap.put("ticketId", "abc");
            ticketMap.put("ticketsBooked", 1);
            ticketMap.put("eventId", "event1");
            ticketMap.put("paymentId", "payment1");
            ticketList.add(ticketMap);
        }
        if (userId == 2L)
        {
            Map<String, Object> ticketMap = new HashMap<>();
            ticketMap.put("ticketId", "ticket2");
            ticketMap.put("ticketsBooked", 1);
            ticketMap.put("eventId", "event2");
            ticketMap.put("paymentId", "payment2");
            ticketList.add(ticketMap);
        }
        if (userId == 3L)
        {
            Map<String, Object> ticketMap = new HashMap<>();
            ticketMap.put("ticketId", "ticket3");
            ticketMap.put("ticketsBooked", 1);
            ticketMap.put("eventId", "event3");
            ticketMap.put("paymentId", "payment3");
            ticketList.add(ticketMap);
        }

        return ticketList;
    }
}
