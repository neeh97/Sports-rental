package com.group1.sports_rental.ProductSearch;

import com.group1.sports_rental.Advertisement.ProductAdvertisement.IProductAdvertisementDao;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;

import java.util.List;

public interface IProductSearchFilter
{

    List<ProductAdvertisement> applyFilters(String city, IProductAdvertisementDao productAdvertisementDao);
}
