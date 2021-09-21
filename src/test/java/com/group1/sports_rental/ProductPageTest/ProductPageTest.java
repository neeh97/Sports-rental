package com.group1.sports_rental.ProductPageTest;
import com.group1.sports_rental.ProductPage.ProductPageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;

public class ProductPageTest
{

    @Test
   public void displayProductNameTest()
    {
        ProductPageMock productPageMock =new ProductPageMock();
        ProductPageService productPageService = ProductPageService.instance();
        Assertions.assertEquals("Yoga mat", productPageService.displayProduct("pr_1234",productPageMock).getProductName());
    }

    @Test
    public void displayProductCategoryTest()
    {
        ProductPageMock productPageMock =new ProductPageMock();
        ProductPageService productPageService = ProductPageService.instance();
        Assertions.assertEquals("equipment", productPageService.displayProduct("pr_1234",productPageMock).getCategory());
    }

    @Test
    public void displayProductSportsTypeTest()
    {
        ProductPageMock productPageMock =new ProductPageMock();
        ProductPageService productPageService = ProductPageService.instance();
        Assertions.assertEquals("Fitness&Exercise", productPageService.displayProduct("pr_1234",productPageMock).getSportsType());
    }

    @Test
    public void displayProductBrandTest()
    {
        ProductPageMock productPageMock =new ProductPageMock();
        ProductPageService productPageService = ProductPageService.instance();
        Assertions.assertEquals("Decthlon", productPageService.displayProduct("pr_1234",productPageMock).getBrand());
    }

    @Test
    public void displayProductCostTest()
    {
        ProductPageMock productPageMock =new ProductPageMock();
        ProductPageService productPageService = ProductPageService.instance();
        Assertions.assertEquals(50, productPageService.displayProduct("pr_1234",productPageMock).getRentalCost());
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
