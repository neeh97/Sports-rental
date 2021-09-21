package com.group1.sports_rental.AdvertisementTest.ProductAdvertisement;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.sql.Date;

public class ProductAdvertisementTests
{
    @Test
    public void validateProductNameTest()
    {
        ProductAdvertisement productAdvertisement = new ProductAdvertisement();
        productAdvertisement.setProductName("NIKE FOOTBALL");
        Assertions.assertTrue(productAdvertisement.validateProductName());
    }

    @Test
    public void validateProductNameNullTest()
    {
        ProductAdvertisement productAdvertisement = new ProductAdvertisement();
        productAdvertisement.setProductName(null);
        Assertions.assertFalse(productAdvertisement.validateProductName());
    }

    @Test
    public void validateProductDescriptionTest()
    {
        ProductAdvertisement productAdvertisement = new ProductAdvertisement();
        productAdvertisement.setProductDescription("This football is manufactured by Nike. It is made up of rubber.");
        Assertions.assertTrue(productAdvertisement.validateProductDescription());
    }

    @Test
    public void validateProductDescriptionNullTest()
    {
        ProductAdvertisement productAdvertisement = new ProductAdvertisement();
        productAdvertisement.setProductDescription(null);
        Assertions.assertFalse(productAdvertisement.validateProductDescription());
    }

    @Test
    public void validateRentalCostTest()
    {
        ProductAdvertisement productAdvertisement= new ProductAdvertisement();
        productAdvertisement.setRentalCost(5.6f);
        Assertions.assertTrue(productAdvertisement.validateRentalCost());
    }

    @Test
    public void validateRentalCostNegativeTest()
    {
        ProductAdvertisement productAdvertisement= new ProductAdvertisement();
        productAdvertisement.setRentalCost(-0);
        Assertions.assertFalse(productAdvertisement.validateRentalCost());
    }

    @Test
    public void validateRentalCostZeroTest()
    {
        ProductAdvertisement productAdvertisement= new ProductAdvertisement();
        productAdvertisement.setRentalCost(0.0f);
        Assertions.assertFalse(productAdvertisement.validateRentalCost());
    }

    @Test
    public void validateDateRangeTest() throws ParseException {
        ProductAdvertisement productAdvertisement = new ProductAdvertisement();
        Date startDate=Date.valueOf("2021-03-01");
        Date endDate=Date.valueOf("2021-03-04");
        productAdvertisement.setStartDate(startDate);
        productAdvertisement.setEndDate(endDate);
        Assertions.assertEquals(-1, productAdvertisement.validateDateRange());
    }

    @Test
    public void startDateAfterEndDateTest() throws ParseException
    {
        ProductAdvertisement productAdvertisement= new ProductAdvertisement();
        Date startDate=Date.valueOf("2021-03-04");
        Date endDate=Date.valueOf("2021-03-01");
        productAdvertisement.setStartDate(startDate);
        productAdvertisement.setEndDate(endDate);
        Assertions.assertEquals(1,productAdvertisement.validateDateRange());
    }

    @Test
    public void startDateEndDateSameTest() throws ParseException
    {
        ProductAdvertisement productAdvertisement= new ProductAdvertisement();
        Date startDate=Date.valueOf("2021-03-04");
        Date endDate=Date.valueOf("2021-03-04");
        productAdvertisement.setStartDate(startDate);
        productAdvertisement.setEndDate(endDate);
        Assertions.assertEquals(0,productAdvertisement.validateDateRange());
    }

    @Test
    public void imageUploadTest() throws IOException
    {
        FileInputStream productImage = new FileInputStream("src/main/resources/test/yoga-mat-500x500.jpg");
        MockMultipartFile multipartFile = new MockMultipartFile("image", productImage);
        ProductAdvertisement productAdvertisement= new ProductAdvertisement();
        Assertions.assertTrue(productAdvertisement.validateImageUpload(multipartFile));
    }

    @Test
    public void  imageUploadEmptyTest() throws IOException
    {
        FileInputStream productImage = new FileInputStream("src/main/resources/test/test.jpg");
        MockMultipartFile multipartFile = new MockMultipartFile("image", productImage);
        ProductAdvertisement productAdvertisement= new ProductAdvertisement();
        Assertions.assertFalse(productAdvertisement.validateImageUpload(multipartFile));
    }

    @Test
    public void insertProductData()
    {
        ProductAdvertisement productAdvertisement = new ProductAdvertisement();
        IProductAdvertisementDao productAdvertisementDao = new ProductAdvertisementDaoMock();
        IProductAdvertisementService productAdvertisementService = new ProductAdvertisementService();
        try
        {
            productAdvertisementService.insertProductData(productAdvertisement,productAdvertisementDao);
        }
        catch (Exception exception)
        {
            Assertions.fail("Product advertisement insertion failed"+exception.getMessage());
        }
    }
}

