package com.group1.sports_rental.EventSearch;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import com.group1.sports_rental.Advertisement.EventAdvertisement.IEventAdvertisementDao;

import java.util.List;

public interface IEventSearchService
{

    List<EventAdvertisement> getDefaultSearchList(String city, IEventAdvertisementDao eventAdvertisementDao);
    List<EventAdvertisement> searchEventName(String searchText, String city, IEventAdvertisementDao eventAdvertisementDao);
}
