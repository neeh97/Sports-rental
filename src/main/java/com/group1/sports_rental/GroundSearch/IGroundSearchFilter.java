package com.group1.sports_rental.GroundSearch;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.IGroundAdvertisementDao;

import java.util.List;

public interface IGroundSearchFilter
{

    List<GroundAdvertisement> applyFilters(String city, IGroundAdvertisementDao groundAdvertisementDao);
}
