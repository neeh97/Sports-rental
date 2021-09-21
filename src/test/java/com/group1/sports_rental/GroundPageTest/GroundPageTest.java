package com.group1.sports_rental.GroundPageTest;

import com.group1.sports_rental.GroundPage.GroundPageService;
import com.group1.sports_rental.ProductPage.ProductPageService;
import com.group1.sports_rental.ProductPageTest.ProductPageMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;

public class GroundPageTest
{
    @Test
    public void displayGroundNameTest()
    {
        GroundPageMock groundPageMock =new GroundPageMock();
        GroundPageService groundPageService = GroundPageService.instance();
        Assertions.assertEquals("LB Stadium", groundPageService.displayGround("gr_1000",groundPageMock).getGroundName());
    }

    @Test
    public void displayGroundSizeTest()
    {
        GroundPageMock groundPageMock =new GroundPageMock();
        GroundPageService groundPageService = GroundPageService.instance();
        Assertions.assertEquals("100 mts", groundPageService.displayGround("gr_1000",groundPageMock).getSize());
    }

    @Test
    public void displayGroundCityTest()
    {
        GroundPageMock groundPageMock =new GroundPageMock();
        GroundPageService groundPageService = GroundPageService.instance();
        Assertions.assertEquals("Halifax", groundPageService.displayGround("gr_1000",groundPageMock).getCity());
    }

    @Test
    public void displayGroundAddressTest()
    {
        GroundPageMock groundPageMock =new GroundPageMock();
        GroundPageService groundPageService = GroundPageService.instance();
        Assertions.assertEquals("Nova Scotia", groundPageService.displayGround("gr_1000",groundPageMock).getAddress());
    }

    @Test
    public void displayGroundSeatingCapacityTest()
    {
        GroundPageMock groundPageMock =new GroundPageMock();
        GroundPageService groundPageService = GroundPageService.instance();
        Assertions.assertEquals("100", groundPageService.displayGround("gr_1000",groundPageMock).getSeatingCapacity());
    }

    @Test
    public void displayGroundCostTest()
    {
        GroundPageMock groundPageMock =new GroundPageMock();
        GroundPageService groundPageService = GroundPageService.instance();
        Assertions.assertEquals(2000, groundPageService.displayGround("gr_1000",groundPageMock).getRentalCost());
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
