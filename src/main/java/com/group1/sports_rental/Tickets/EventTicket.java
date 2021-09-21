package com.group1.sports_rental.Tickets;

import java.sql.SQLException;

public class EventTicket
{
    IEventTicketService eventTicketService = new EventTicketService();

    public Float calculateTotalTicketPrice(String eventId, Integer ticketsBooked, IEventTicketDao eventTicketDao)
    {
        Float perTicketPrice = eventTicketService.fetchTicketPrice(eventId,eventTicketDao);
        Float totalPrice = perTicketPrice*ticketsBooked;
        return totalPrice;
    }

    public void changeTicketCount(String eventId, Integer totalTicketsBooked,IEventTicketDao eventTicketDao) throws SQLException
    {
        Integer ticketCount = eventTicketService.fetchTicketCount(eventId,eventTicketDao);
        Integer ticketsLeft = ticketCount - totalTicketsBooked;
        eventTicketService.updateTicketCount(eventId,ticketsLeft,eventTicketDao);
    }
}
