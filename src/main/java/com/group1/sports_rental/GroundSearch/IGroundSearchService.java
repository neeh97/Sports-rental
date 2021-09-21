package com.group1.sports_rental.GroundSearch;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.IGroundAdvertisementDao;

import java.util.List;

public interface IGroundSearchService
{

    List<GroundAdvertisement> setDefaultGroundsList(String city, IGroundAdvertisementDao groundAdvertisementDao);
    List<GroundAdvertisement> searchGroundText(String searchText, String city, IGroundAdvertisementDao groundAdvertisementDao);
}
