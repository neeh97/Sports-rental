package com.group1.sports_rental.EventSearch;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import com.group1.sports_rental.Advertisement.EventAdvertisement.IEventAdvertisementDao;

import java.util.ArrayList;
import java.util.List;

public class EventSearchService implements IEventSearchService {


    static EventSearchService instance = null;

    public static EventSearchService instance()
    {
        if (instance == null)
        {
            instance = new EventSearchService();
        }
        return instance;
    }

    public List<EventAdvertisement> getDefaultSearchList(String city, IEventAdvertisementDao eventAdvertisementDao)
    {
        List<EventAdvertisement> defaultSearchList = new ArrayList<>();
        try
        {
            defaultSearchList = eventAdvertisementDao.getDefaultEvents(city);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return defaultSearchList;
    }

    public List<EventAdvertisement> searchEventName(String searchText, String city, IEventAdvertisementDao eventAdvertisementDao)
    {
        List<EventAdvertisement> searchResults = new ArrayList<>();
        try
        {
            searchResults = eventAdvertisementDao.searchEventName(city, searchText);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return searchResults;
    }
}
