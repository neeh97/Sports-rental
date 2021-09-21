package com.group1.sports_rental.Advertisement.GroundAdvertisement;

public interface IGroundAdvertisementService
{
    String fetchCityName(String state,String city,IGroundAdvertisementDao groundAdvertisementDao);
    void insertGroundData(GroundAdvertisement groundAdvertisement,IGroundAdvertisementDao groundAdvertisementDao);
}
