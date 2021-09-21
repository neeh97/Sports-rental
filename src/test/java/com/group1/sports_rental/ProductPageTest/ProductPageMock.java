package com.group1.sports_rental.ProductPageTest;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import com.group1.sports_rental.ProductPage.IProductPageDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;

public class ProductPageMock implements IProductPageDao
{

    public ProductAdvertisement displayProduct(String productId)
    {
        AbstractFactory factory = new ConcreteFactory();
        ProductAdvertisement productAdvertisement = factory.createProduct();
        if (productId.equals("pr_1234")) {
            productAdvertisement.setProductName("Yoga mat");
            productAdvertisement.setCategory("equipment");
            productAdvertisement.setSportsType("Fitness&Exercise");
            productAdvertisement.setBrand("Decthlon");
            productAdvertisement.setRentalCost(50);
            productAdvertisement.setStartDate(Date.valueOf("2021-03-23"));
            productAdvertisement.setEndDate(Date.valueOf("2025-03-23"));
        }
        return productAdvertisement;
    }

    public InputStream getProductImage(String productId)
    {
        File productImage = new File("src/main/resources/test/yoga-mat-500x500.jpg");
        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(productImage);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return inputStream;
    }
}
