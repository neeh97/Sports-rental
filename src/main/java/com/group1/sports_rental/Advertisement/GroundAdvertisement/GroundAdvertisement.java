package com.group1.sports_rental.Advertisement.GroundAdvertisement;

import com.group1.sports_rental.Advertisement.Advertisement;

public class GroundAdvertisement extends Advertisement
{
    private final IGroundAdvertisementService groundAdvertisementService;
    private String address;
    private String size;
    private String seatingCapacity;
    private String city;
    private String state;
    private String groundName;

    public GroundAdvertisement()
    {
        groundAdvertisementService = new GroundAdvertisementService();
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getSeatingCapacity()
    {
        return seatingCapacity;
    }

    public void setSeatingCapacity(String seatingCapacity)
    {
        this.seatingCapacity = seatingCapacity;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getGroundName()
    {
        return groundName;
    }

    public void setGroundName(String groundName)
    {
        this.groundName = groundName;
    }

    public Boolean validateCityExists(IGroundAdvertisementDao groundAdvertisementDao)
    {
        String city = groundAdvertisementService.fetchCityName(this.state,this.city,groundAdvertisementDao);
        if (city.isEmpty())
        {
            return false;
        }
        return true;
    }

    public Boolean validateGroundName()
    {
        if (this.groundName == null || this.groundName.isEmpty())
        {
            return false;
        }
        return true;
    }

    public Boolean validateAddress()
    {
        if (this.address == null || this.address.isEmpty())
        {
            return false;
        }
        return true;
    }
}
