package com.group1.sports_rental.Tickets;

import java.sql.SQLException;

public interface IEventTicketService
{
    Float fetchTicketPrice(String eventId, IEventTicketDao eventTicketDao);
    String insertTicketData(String eventId,Integer tickets, Float totalTicketPrice,Long userId,IEventTicketDao eventTicketDao);
    Integer fetchTicketCount(String eventId,IEventTicketDao eventTicketDao);
    void updateTicketCount(String eventId,Integer ticketsLeft,IEventTicketDao eventTicketDao) throws SQLException;
}
