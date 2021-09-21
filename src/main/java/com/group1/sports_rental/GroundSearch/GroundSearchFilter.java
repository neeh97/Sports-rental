package com.group1.sports_rental.GroundSearch;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.IGroundAdvertisementDao;
import java.util.ArrayList;
import java.util.List;

public class GroundSearchFilter implements IGroundSearchFilter{

    private String sportsType;
    private String location;

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getSportsType()
    {
        return sportsType;
    }

    public void setSportsType(String sportsType)
    {
        this.sportsType = sportsType;
    }

    public List<GroundAdvertisement> applyFilters(String city, IGroundAdvertisementDao groundAdvertisementDao)
    {
        List<GroundAdvertisement> groundAdvertisementList = new ArrayList<>();
        try {
            groundAdvertisementList = groundAdvertisementDao.applyFilters(city, this.sportsType, this.location);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return groundAdvertisementList;
    }
}
