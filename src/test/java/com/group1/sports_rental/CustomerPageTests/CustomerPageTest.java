package com.group1.sports_rental.CustomerPageTests;

import com.group1.sports_rental.AdvertisementTest.EventAdvertisementTest.EventAdvertisementDaoMock;
import com.group1.sports_rental.CustomerPage.*;
import com.group1.sports_rental.EventTicketTest.EventTicketDaoMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CustomerPageTest {

    @Test
    public void getRentalHistoryTest()
    {
        ICustomerPageDao customerPageMock = new CustomerPageMock();
        ICustomerPageService customerPageService = CustomerPageService.instance();
        List<RentalHistory> rentalHistoryList = customerPageService.getCustomerRentalHistory(1L, customerPageMock);
        Assertions.assertEquals("Yoga mat", rentalHistoryList.get(0).getProductName());
    }

    @Test
    public void emptyRentalHistoryTest()
    {
        ICustomerPageDao customerPageMock = new CustomerPageMock();
        ICustomerPageService customerPageService = CustomerPageService.instance();
        List<RentalHistory> rentalHistoryList = customerPageService.getCustomerRentalHistory(2L, customerPageMock);
        Assertions.assertEquals(0, rentalHistoryList.size());
    }

    @Test
    public void topUpCreditsTest()
    {
        CreditTopup creditTopup = new CreditTopup();
        creditTopup.setCreditAmount("100");
        CustomerPageMock customerPageDao = new CustomerPageMock();
        creditTopup.topUpCredits(1L, customerPageDao);
        Assertions.assertEquals(100, customerPageDao.getCredits());
    }

    @Test
    public void getCreditsOfUserTest()
    {
        ICustomerPageDao customerPageDao = new CustomerPageMock();
        ICustomerCreditService customerCreditService = CustomerCreditService.instance();
        CustomerCredit customerCredit = customerCreditService.getCreditsOfUser(1L, customerPageDao);
        Assertions.assertEquals(100F, customerCredit.getCredits());
    }

    @Test
    public void getNoCreditsOfUserTest()
    {
        ICustomerPageDao customerPageDao = new CustomerPageMock();
        ICustomerCreditService customerCreditService = CustomerCreditService.instance();
        CustomerCredit customerCredit = customerCreditService.getCreditsOfUser(2L, customerPageDao);
        Assertions.assertNull(customerCredit);
    }

    @Test
    public void refundCreditsTest()
    {
        CustomerPageMock customerPageDao = new CustomerPageMock();
        ICustomerCreditService customerCreditService = CustomerCreditService.instance();
        CreditTopup creditTopup = new CreditTopup();
        creditTopup.setCreditAmount("100");
        creditTopup.topUpCredits(1L, customerPageDao);
        customerCreditService.refundCredits(1L, "api", customerPageDao);
        Assertions.assertEquals(0F, customerPageDao.getCredits());
    }

    @Test
    public void fetchTicketsOfCustomerTest()
    {
        ICustomerTicketService customerTicketService = CustomerTicketService.instance();
        EventAdvertisementDaoMock eventAdvertisementDaoMock = new EventAdvertisementDaoMock();
        EventTicketDaoMock eventTicketDaoMock = new EventTicketDaoMock();
        List<CustomerTicket> ticketList = customerTicketService.fetchCustomerActiveTickets(1L, eventTicketDaoMock, eventAdvertisementDaoMock);
        Assertions.assertEquals("Metallica Concert", ticketList.get(0).getEventName());
    }

    @Test
    public void fetchPastTicketsTest()
    {
        ICustomerTicketService customerTicketService = CustomerTicketService.instance();
        EventAdvertisementDaoMock eventAdvertisementDaoMock = new EventAdvertisementDaoMock();
        EventTicketDaoMock eventTicketDaoMock = new EventTicketDaoMock();
        List<CustomerTicket> ticketList = customerTicketService.fetchCustomerActiveTickets(2L, eventTicketDaoMock, eventAdvertisementDaoMock);
        Assertions.assertEquals(0, ticketList.size());
    }

    @Test
    public void noEventForTicketTest()
    {
        ICustomerTicketService customerTicketService = CustomerTicketService.instance();
        EventAdvertisementDaoMock eventAdvertisementDaoMock = new EventAdvertisementDaoMock();
        EventTicketDaoMock eventTicketDaoMock = new EventTicketDaoMock();
        List<CustomerTicket> ticketList = customerTicketService.fetchCustomerActiveTickets(3L, eventTicketDaoMock, eventAdvertisementDaoMock);
        Assertions.assertEquals(0, ticketList.size());
    }
}
