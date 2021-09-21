package com.group1.sports_rental.ProductSearch;

import com.group1.sports_rental.Advertisement.ProductAdvertisement.IProductAdvertisementDao;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchService implements IProductSearchService
{

    static ProductSearchService instance = null;

    public static ProductSearchService instance()
    {
        if (instance == null)
        {
            instance = new ProductSearchService();
        }
        return instance;
    }

    public List<ProductAdvertisement> setDefaultSearchPage(String city, IProductAdvertisementDao productAdvertisementDao)
    {
        List<ProductAdvertisement> defaultProductsList = new ArrayList<>();
        try
        {
            defaultProductsList = productAdvertisementDao.getDefaultSearchProducts(city);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return defaultProductsList;
    }

    public List<ProductAdvertisement> searchProducts(String name, String city, IProductAdvertisementDao productAdvertisementDao)
    {
        List<ProductAdvertisement> searchResultList = new ArrayList<>();
        try
        {
            searchResultList = productAdvertisementDao.searchProductName(city, name);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return searchResultList;
    }
}
