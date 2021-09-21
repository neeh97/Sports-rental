package com.group1.sports_rental.AdvertisementTest.GroundAdvertisement;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroundAdvertisementTest
{
    @Test
    public void validateCityExistsTest()
    {
        GroundAdvertisementDaoMock groundAdvertisementDaoMock = new GroundAdvertisementDaoMock();
        GroundAdvertisement groundAdvertisement = new GroundAdvertisement();
        groundAdvertisement.setCity("Halifax");
        groundAdvertisement.setState("Nova Scotia");
        Assertions.assertEquals("Halifax",groundAdvertisementDaoMock.fetchCity(groundAdvertisement.getState(),groundAdvertisement.getCity()));
    }

    @Test
    public void validateCityNotExistsTest()
    {
        GroundAdvertisementDaoMock groundAdvertisementDaoMock = new GroundAdvertisementDaoMock();
        GroundAdvertisement groundAdvertisement = new GroundAdvertisement();
        groundAdvertisement.setCity("Toronto");
        groundAdvertisement.setState("Nova Scotia");
        Assertions.assertNull(groundAdvertisementDaoMock.fetchCity(groundAdvertisement.getState(), groundAdvertisement.getCity()));
    }

    @Test
    public void validateAddressTest()
    {
        GroundAdvertisement groundAdvertisement = new GroundAdvertisement();
        groundAdvertisement.setAddress("B044, Wellington street");
        Assertions.assertTrue(groundAdvertisement.validateAddress());
    }

    @Test
    public void validateAddressNotExistsTest()
    {
        GroundAdvertisement groundAdvertisement = new GroundAdvertisement();
        Assertions.assertFalse(groundAdvertisement.validateAddress());
    }

    @Test
    public void validateGroundNameTest()
    {
        GroundAdvertisement groundAdvertisement = new GroundAdvertisement();
        groundAdvertisement.setGroundName("Siri fort");
        Assertions.assertTrue(groundAdvertisement.validateGroundName());
    }

    @Test
    public void validateGroundNameNullTest()
    {
        GroundAdvertisement groundAdvertisement = new GroundAdvertisement();
        Assertions.assertFalse(groundAdvertisement.validateGroundName());
    }
}
