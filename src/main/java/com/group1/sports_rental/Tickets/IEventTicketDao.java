package com.group1.sports_rental.Tickets;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IEventTicketDao
{
    Float fetchTicketPrice(String eventId);
    String insertTicketData(String eventId,Integer tickets, Float totalTicketPrice,Long userId);
    Integer fetchTicketCount(String eventId);
    void updateTicketCount(String eventId,Integer ticketsLeft) throws SQLException;
    List<Map<String, Object>> fetchEventTicketsOfUser(Long userId);
}
