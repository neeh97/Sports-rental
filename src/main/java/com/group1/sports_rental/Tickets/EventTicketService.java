package com.group1.sports_rental.Tickets;

import java.sql.SQLException;

public class EventTicketService  implements IEventTicketService
{
    public Float fetchTicketPrice(String eventId,IEventTicketDao eventTicketDao)
    {
        return eventTicketDao.fetchTicketPrice(eventId);
    }

    public String insertTicketData(String eventId, Integer tickets, Float totalTicketPrice, Long userId,IEventTicketDao eventTicketDao)
    {
        return eventTicketDao.insertTicketData(eventId, tickets,totalTicketPrice,userId);
    }

    public Integer fetchTicketCount(String eventId,IEventTicketDao eventTicketDao)
    {
        return eventTicketDao.fetchTicketCount(eventId);
    }

    public void updateTicketCount(String eventId, Integer ticketsLeft,IEventTicketDao eventTicketDao) throws SQLException
    {
        eventTicketDao.updateTicketCount(eventId,ticketsLeft);
    }
}
