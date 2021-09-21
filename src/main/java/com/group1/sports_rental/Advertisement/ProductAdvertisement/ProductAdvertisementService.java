package com.group1.sports_rental.Advertisement.ProductAdvertisement;

public class ProductAdvertisementService implements IProductAdvertisementService
{
    public void insertProductData(ProductAdvertisement productAdvertisement, IProductAdvertisementDao productAdvertisementDao)
    {
        productAdvertisementDao.insertProductData(productAdvertisement);
    }
}
