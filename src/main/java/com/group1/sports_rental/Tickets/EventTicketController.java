package com.group1.sports_rental.Tickets;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class EventTicketController
{
    IEventTicketDao eventTicketDao = new EventTicketDao();
    EventTicket eventTicket = new EventTicket();
    IEventTicketService eventTicketService = new EventTicketService();

    @PostMapping("/ticket/book")
    public String bookTicket(Model model, HttpServletRequest httpServletRequest, EventAdvertisement eventAdvertisement)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            Long userId = (Long) httpSession.getAttribute("userId");
            String eventId = eventAdvertisement.getEventId();
            Integer ticketsBooked = eventAdvertisement.getTicketsBookedByUser();
            Float totalTicketPrice = eventTicket.calculateTotalTicketPrice(eventId, ticketsBooked,eventTicketDao);
            eventAdvertisement.setTicketTotalPrice(totalTicketPrice);
            model.addAttribute("eventAdvertisement", eventAdvertisement);
            model.addAttribute("userId", userId);
            model.addAttribute("eventId",eventId);
            return "bookTicket";
        }
    }

    @GetMapping("/ticket/book/confirm/{eventId}/{totalTicketPrice}/{tickets}")
    public String confirmBookTicket(Model model, HttpServletRequest httpServletRequest, @PathVariable(name="eventId") String eventId,@PathVariable(name="tickets") String tickets,@PathVariable(name="totalTicketPrice") String totalTicketPrice) throws SQLException
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            Float amount = Float.parseFloat(totalTicketPrice);
            Integer totalTicketsBooked = Integer.parseInt(tickets);
            Long userId = (Long) httpSession.getAttribute("userId");
            String ticketId = eventTicketService.insertTicketData(eventId,totalTicketsBooked,amount,userId,eventTicketDao);
            eventTicket.changeTicketCount(eventId,totalTicketsBooked,eventTicketDao);
            model.addAttribute("ticketId", ticketId);
            return "ticketConfirm";
        }
    }
}
