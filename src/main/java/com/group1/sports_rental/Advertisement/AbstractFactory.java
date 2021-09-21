package com.group1.sports_rental.Advertisement;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;

public interface AbstractFactory
{
    ProductAdvertisement createProduct();
    GroundAdvertisement createGround();
    EventAdvertisement createEvent();
}
