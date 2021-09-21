package com.group1.sports_rental.Advertisement.ProductAdvertisement;

import java.util.List;

public interface IProductAdvertisementDao
{
    void insertProductData(ProductAdvertisement productAdvertisement);
    List<ProductAdvertisement> filterProducts(String city, String location, String category, String sportsType);
    List<ProductAdvertisement> getDefaultSearchProducts(String city);
    List<ProductAdvertisement> searchProductName(String city, String name);
    ProductAdvertisement findOne(String productId);
}
