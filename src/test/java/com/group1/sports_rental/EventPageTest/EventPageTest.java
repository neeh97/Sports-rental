package com.group1.sports_rental.EventPageTest;

import com.group1.sports_rental.EventPage.EventPageService;
import com.group1.sports_rental.EventPage.IEventPageService;
import com.group1.sports_rental.ProductPage.ProductPageService;
import com.group1.sports_rental.ProductPageTest.ProductPageMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

public class EventPageTest
{
    @Test
    public void showEventTest()
    {
        String eventId = "ev_12fyhhb";
        EventPageDaoMock eventPageDaoMock = new EventPageDaoMock();
        IEventPageService eventPageService = new EventPageService();
        Assertions.assertEquals("Football event",eventPageService.showEvent(eventId,eventPageDaoMock).getEventName());
    }

    @Test
    public void displayImageTest()
    {
        ProductPageMock productPageMock = new ProductPageMock();
        ProductPageService productPageService = ProductPageService.instance();
        ByteArrayOutputStream byteArrayOutputStream = productPageService.displayProductImage("pr_1234",productPageMock);
        Assertions.assertNotEquals(null,byteArrayOutputStream);
    }
}
