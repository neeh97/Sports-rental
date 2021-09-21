package com.group1.sports_rental.ProductSearch;

import com.group1.sports_rental.Advertisement.ProductAdvertisement.IProductAdvertisementDao;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;

import java.util.List;

public interface IProductSearchService
{

    List<ProductAdvertisement> setDefaultSearchPage(String city, IProductAdvertisementDao productAdvertisementDao);
    List<ProductAdvertisement> searchProducts(String name, String city, IProductAdvertisementDao productAdvertisementDao);
}
