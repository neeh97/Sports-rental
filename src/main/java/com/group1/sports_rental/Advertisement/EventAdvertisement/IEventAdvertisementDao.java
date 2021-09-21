package com.group1.sports_rental.Advertisement.EventAdvertisement;

import java.util.List;

public interface IEventAdvertisementDao
{
    String fetchCity(String state,String city);
    void insertEventRecord(EventAdvertisement eventAdvertisement);
    List<EventAdvertisement> applyFilters(String query, String location, String minimumPrice, String maximumPrice);
    List<EventAdvertisement> getDefaultEvents(String city);
    List<EventAdvertisement> searchEventName(String city, String searchText);
    EventAdvertisement findOne(String eventId);
}
