package com.group1.sports_rental.EventTicketTest;

import com.group1.sports_rental.Tickets.EventTicket;
import com.group1.sports_rental.Tickets.EventTicketService;
import com.group1.sports_rental.Tickets.IEventTicketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

public class EventTicketTest
{
    @Test
    public void calculateTotalTicketPriceTest()
    {
        EventTicket eventTicket = new EventTicket();
        EventTicketDaoMock eventTicketDaoMock = new EventTicketDaoMock();
        String eventId = "ev_12345abcd";
        Integer ticketsBooked = 1;
        Assertions.assertEquals(176.0F,eventTicket.calculateTotalTicketPrice(eventId,ticketsBooked,eventTicketDaoMock));
    }

    @Test
    public void fetchTicketPriceTest()
    {
        IEventTicketService eventTicketService = new EventTicketService();
        EventTicketDaoMock eventTicketDaoMock = new EventTicketDaoMock();
        Assertions.assertEquals(176.0F,eventTicketService.fetchTicketPrice("ev_12345abcd",eventTicketDaoMock));
    }

    @Test
    public void fetchTicketCountTest()
    {
        IEventTicketService eventTicketService = new EventTicketService();
        EventTicketDaoMock eventTicketDaoMock = new EventTicketDaoMock();
        Assertions.assertEquals(5,eventTicketService.fetchTicketCount("ev_12345abcd",eventTicketDaoMock));
    }

    //Referred https://git.cs.dal.ca/rhawkey/csci5308/-/blob/master/TDD/TutorialSampleCode/intellijtdd/src/test/java/UserTest.java
    @Test
    public void updateTicketCountTest() throws SQLException {
        IEventTicketService eventTicketService = new EventTicketService();
        EventTicketDaoMock eventTicketDaoMock = new EventTicketDaoMock();
        try
        {
            eventTicketService.updateTicketCount("ev_12345abcd",400,eventTicketDaoMock);
        }
        catch( SQLException sqlException)
        {
            Assertions.fail(sqlException.getMessage());
        }
    }

    @Test
    public void insertTicketData()
    {
        IEventTicketService eventTicketService = new EventTicketService();
        EventTicketDaoMock eventTicketDaoMock = new EventTicketDaoMock();
        Assertions.assertEquals("ok1234",eventTicketService.insertTicketData("ev_12345abcd",1,55F,12L,eventTicketDaoMock));
    }

    @Test
    public void changeTicketCountTest()
    {
        EventTicket eventTicket = new EventTicket();
        String eventId = "ev_12345abcd";
        Integer ticketsBooked = 5;
        EventTicketDaoMock eventTicketDaoMock = new EventTicketDaoMock();
        try
        {
            eventTicket.changeTicketCount(eventId,ticketsBooked,eventTicketDaoMock);
        }
        catch(SQLException sqlException)
        {
            Assertions.fail(sqlException.getMessage());
        }
    }
}
