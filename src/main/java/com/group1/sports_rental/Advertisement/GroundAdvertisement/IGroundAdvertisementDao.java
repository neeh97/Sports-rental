package com.group1.sports_rental.Advertisement.GroundAdvertisement;

import java.util.List;

public interface IGroundAdvertisementDao
{
    String fetchCity(String state,String city);
    void insertGround(GroundAdvertisement groundAdvertisement);
    List<GroundAdvertisement> applyFilters(String city, String sportsType, String location);
    List<GroundAdvertisement> getDefaultGrounds(String city);
    List<GroundAdvertisement> searchGroundName(String city, String searchText);
    GroundAdvertisement findOne(String groundId);
}
