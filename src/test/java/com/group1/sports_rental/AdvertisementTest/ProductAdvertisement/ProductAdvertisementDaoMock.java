package com.group1.sports_rental.AdvertisementTest.ProductAdvertisement;
import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.IProductAdvertisementDao;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import org.springframework.util.StringUtils;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ProductAdvertisementDaoMock implements IProductAdvertisementDao
{
    private final static AbstractFactory factory = new ConcreteFactory();

    public void insertProductData(ProductAdvertisement productAdvertisement)
    {
        Date startDate=Date.valueOf("2021-03-04");
        Date endDate=Date.valueOf("2021-03-04");
        productAdvertisement = factory.createProduct();
        productAdvertisement.setProductName("Yoga mat");
        productAdvertisement.setProductDescription("Blue is color");
        productAdvertisement.setRentalCost(5f);
        productAdvertisement.setStartDate(startDate);
        productAdvertisement.setEndDate(endDate);
        productAdvertisement.setCategory("equipment");
        productAdvertisement.setSportsType("Fitness&Exercise");
        productAdvertisement.setBrand("Decathlon");
        productAdvertisement.setUserId(111L);
    }

    public List<ProductAdvertisement> filterProducts(String city, String location, String category, String sportsType)
    {
        List<ProductAdvertisement> productAdvertisementList = new ArrayList<>();
        if (StringUtils.hasText(location) && location.equals("Ameerpet")) {
            ProductAdvertisement productAdvertisement = factory.createProduct();
            productAdvertisement.setProductName("Yoga mat");
            productAdvertisement.setProductDescription("Blue is color");
            productAdvertisement.setRentalCost(5f);
            productAdvertisement.setCategory("equipment");
            productAdvertisement.setSportsType("Fitness&Exercise");
            productAdvertisement.setBrand("Decathlon");
            productAdvertisement.setUserId(111L);
            productAdvertisementList.add(productAdvertisement);
        }
        if (StringUtils.hasText(category) && category.equals("equipment"))
        {
            ProductAdvertisement productAdvertisement = factory.createProduct();
            productAdvertisement.setProductName("Cricket bat");
            productAdvertisement.setCategory("equipment");
            productAdvertisement.setSportsType("Cricket");
            productAdvertisement.setBrand("SS");
            productAdvertisementList.add(productAdvertisement);
        }
        if (StringUtils.hasText(sportsType) && sportsType.equals("Football"))
        {
            ProductAdvertisement productAdvertisement = factory.createProduct();
            productAdvertisement.setProductName("Nivea Football");
            productAdvertisement.setCategory("equipment");
            productAdvertisement.setSportsType("Football");
            productAdvertisement.setBrand("Nivea");
            productAdvertisementList.add(productAdvertisement);
        }
        return productAdvertisementList;
    }

    public ProductAdvertisement findOne(String productId)
    {
        ProductAdvertisement productAdvertisement = factory.createProduct();
        productAdvertisement.setProductName("Yoga mat");
        productAdvertisement.setProductDescription("Blue is color");
        productAdvertisement.setRentalCost(5f);
        productAdvertisement.setCategory("equipment");
        productAdvertisement.setSportsType("Fitness&Exercise");
        productAdvertisement.setBrand("Decathlon");
        productAdvertisement.setUserId(111L);
        return productAdvertisement;
    }

    public List<ProductAdvertisement> getDefaultSearchProducts(String city)
    {
        List<ProductAdvertisement> productAdvertisementList = new ArrayList<>();
        if (city.equals("Halifax"))
        {
            ProductAdvertisement productAdvertisement = factory.createProduct();
            productAdvertisement.setProductName("Yoga mat");
            productAdvertisement.setProductDescription("Blue is color");
            productAdvertisement.setRentalCost(5f);
            productAdvertisement.setCategory("equipment");
            productAdvertisement.setSportsType("Fitness&Exercise");
            productAdvertisement.setBrand("Decathlon");
            productAdvertisement.setUserId(111L);
            productAdvertisementList.add(productAdvertisement);
        }
        return productAdvertisementList;
    }

    public List<ProductAdvertisement> searchProductName(String city, String name)
    {
        List<ProductAdvertisement> productAdvertisementList = new ArrayList<>();
        if (city.equals("Halifax") && name.equals("Yoga"))
        {
            ProductAdvertisement productAdvertisement = factory.createProduct();
            productAdvertisement.setProductName("Yoga mat");
            productAdvertisement.setProductDescription("Blue is color");
            productAdvertisement.setRentalCost(5f);
            productAdvertisement.setCategory("equipment");
            productAdvertisement.setSportsType("Fitness&Exercise");
            productAdvertisement.setBrand("Decathlon");
            productAdvertisement.setUserId(111l);
            productAdvertisementList.add(productAdvertisement);
        }
        return productAdvertisementList;
    }

}
