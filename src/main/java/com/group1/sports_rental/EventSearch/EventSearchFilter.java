package com.group1.sports_rental.EventSearch;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import com.group1.sports_rental.Advertisement.EventAdvertisement.IEventAdvertisementDao;
import java.util.ArrayList;
import java.util.List;

public class EventSearchFilter implements IEventSearchFilter{

    private String location;
    private String minimumPrice;
    private String maximumPrice;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(String minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public String getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(String maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public List<EventAdvertisement> applyFilters(String city, IEventAdvertisementDao eventAdvertisementDao)
    {
        List<EventAdvertisement> filteredList = new ArrayList<>();
        try
        {
            filteredList = eventAdvertisementDao.applyFilters(city, this.location, this.minimumPrice, this.maximumPrice);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return filteredList;
    }
}
