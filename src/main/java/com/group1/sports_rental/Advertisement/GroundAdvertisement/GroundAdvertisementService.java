package com.group1.sports_rental.Advertisement.GroundAdvertisement;

public class GroundAdvertisementService implements IGroundAdvertisementService
{
    public String fetchCityName(String state, String city, IGroundAdvertisementDao groundAdvertisementDao)
    {
        return groundAdvertisementDao.fetchCity(state,city);
    }

    public void insertGroundData(GroundAdvertisement groundAdvertisement, IGroundAdvertisementDao groundAdvertisementDao)
    {
        groundAdvertisementDao.insertGround(groundAdvertisement);
    }
}
