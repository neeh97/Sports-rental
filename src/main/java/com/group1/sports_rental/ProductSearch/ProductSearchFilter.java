package com.group1.sports_rental.ProductSearch;

import com.group1.sports_rental.Advertisement.ProductAdvertisement.IProductAdvertisementDao;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchFilter implements IProductSearchFilter
{

    private String category;
    private String sportsType;
    private String location;

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getSportsType()
    {
        return sportsType;
    }

    public void setSportsType(String sportsType)
    {
        this.sportsType = sportsType;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public List<ProductAdvertisement> applyFilters(String city, IProductAdvertisementDao productAdvertisementDao)
    {
        List<ProductAdvertisement> filteredList = new ArrayList<>();
        try
        {
            filteredList = productAdvertisementDao.filterProducts(city, this.getLocation(), this.getCategory(), this.getSportsType());
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return filteredList;
    }
}
