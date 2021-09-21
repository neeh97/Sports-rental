package com.group1.sports_rental.CustomerPage;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import com.group1.sports_rental.Advertisement.EventAdvertisement.IEventAdvertisementDao;
import com.group1.sports_rental.Tickets.IEventTicketDao;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CustomerTicketService implements ICustomerTicketService
{

    static CustomerTicketService instance = null;

    public static CustomerTicketService instance()
    {
        if (instance == null)
        {
            instance = new CustomerTicketService();
        }
        return instance;
    }


    public List<CustomerTicket> fetchCustomerActiveTickets(Long userId, IEventTicketDao eventTicketUserDao, IEventAdvertisementDao eventAdvertisementDao)
    {
        List<CustomerTicket> tickets = new ArrayList<>();
        try
        {
            List<Map<String, Object>> ticketsList = eventTicketUserDao.fetchEventTicketsOfUser(userId);
            for (Map<String, Object> ticketMap : ticketsList)
            {
                String eventId = (String) ticketMap.get("eventId");
                if (StringUtils.hasText(eventId))
                {
                    EventAdvertisement eventAdvertisement = eventAdvertisementDao.findOne(eventId);
                    if (eventAdvertisement == null)
                    {
                        break;
                    }
                    Date currentDate = new Date();
                    Date eventDate = new Date(eventAdvertisement.getEventDate().getTime());
                    if (currentDate.compareTo(eventDate) < 0)
                    {
                        CustomerTicket customerTicket = new CustomerTicket();
                        customerTicket.setTicketId((String) ticketMap.get("ticketId"));
                        customerTicket.setTicketsBooked((Integer) ticketMap.get("ticketsBooked"));
                        customerTicket.setEventName(eventAdvertisement.getEventName());
                        customerTicket.setPaymentId((String) ticketMap.get("paymentId"));
                        tickets.add(customerTicket);
                    }
                }
            }

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return tickets;
    }

}
