package com.group1.sports_rental.CustomerPage;

import com.group1.sports_rental.Advertisement.EventAdvertisement.IEventAdvertisementDao;
import com.group1.sports_rental.Tickets.IEventTicketDao;
import java.util.List;

public interface ICustomerTicketService
{

    List<CustomerTicket> fetchCustomerActiveTickets(Long userId, IEventTicketDao eventTicketUserDao, IEventAdvertisementDao eventAdvertisementDao);
}
