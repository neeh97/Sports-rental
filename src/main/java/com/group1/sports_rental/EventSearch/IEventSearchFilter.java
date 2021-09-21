package com.group1.sports_rental.EventSearch;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import com.group1.sports_rental.Advertisement.EventAdvertisement.IEventAdvertisementDao;

import java.util.List;

public interface IEventSearchFilter
{

    List<EventAdvertisement> applyFilters(String city, IEventAdvertisementDao eventAdvertisementDao);
}
