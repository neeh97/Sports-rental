package com.group1.sports_rental.Advertisement.EventAdvertisement;

public interface IEventAdvertisementService
{
    String fetchCityName(String state,String city,IEventAdvertisementDao eventAdvertisementDao);
    void insertEventRecord(EventAdvertisement eventAdvertisement, IEventAdvertisementDao eventAdvertisementDao);
}
