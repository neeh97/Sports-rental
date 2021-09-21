package com.group1.sports_rental.Advertisement;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;

public class ConcreteFactory implements AbstractFactory
{
    public ProductAdvertisement createProduct()
    {
        return new ProductAdvertisement();
    }

    public GroundAdvertisement createGround()
    {
        return new GroundAdvertisement();
    }

    public EventAdvertisement createEvent()
    {
        return new EventAdvertisement();
    }
}
