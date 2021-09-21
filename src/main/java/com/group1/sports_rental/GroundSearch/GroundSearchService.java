package com.group1.sports_rental.GroundSearch;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.IGroundAdvertisementDao;
import java.util.ArrayList;
import java.util.List;

public class GroundSearchService implements IGroundSearchService
{

    static GroundSearchService instance = null;

    public static GroundSearchService instance()
    {
        if (instance == null)
        {
            instance = new GroundSearchService();
        }
        return instance;
    }

    public List<GroundAdvertisement> setDefaultGroundsList(String city, IGroundAdvertisementDao groundAdvertisementDao)
    {
        List<GroundAdvertisement> defaultGroundList = new ArrayList<>();
        try
        {
            defaultGroundList = groundAdvertisementDao.getDefaultGrounds(city);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return defaultGroundList;
    }

    public List<GroundAdvertisement> searchGroundText(String searchText, String city, IGroundAdvertisementDao groundAdvertisementDao)
    {
        List<GroundAdvertisement> searchResults = new ArrayList<>();
        try
        {
            searchResults = groundAdvertisementDao.searchGroundName(city, searchText);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return searchResults;
    }
}
