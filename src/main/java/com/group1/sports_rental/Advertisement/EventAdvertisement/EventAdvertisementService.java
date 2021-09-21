package com.group1.sports_rental.Advertisement.EventAdvertisement;

public class EventAdvertisementService implements IEventAdvertisementService
{
    public String fetchCityName(String state, String city, IEventAdvertisementDao eventAdvertisementDao)
    {
        return eventAdvertisementDao.fetchCity(state,city);
    }

    public void insertEventRecord(EventAdvertisement eventAdvertisement, IEventAdvertisementDao eventAdvertisementDao)
    {
        eventAdvertisementDao.insertEventRecord(eventAdvertisement);
    }
}
