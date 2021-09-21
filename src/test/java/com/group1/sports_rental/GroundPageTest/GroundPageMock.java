package com.group1.sports_rental.GroundPageTest;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.GroundPage.IGroundPageDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;

public class GroundPageMock implements IGroundPageDao
{

    public GroundAdvertisement displayGround(String groundId)
    {
        AbstractFactory factory = new ConcreteFactory();
        GroundAdvertisement groundAdvertisement = factory.createGround();
        if (groundId.equals("gr_1000")) {
            groundAdvertisement.setGroundName("LB Stadium");
            groundAdvertisement.setAddress("Nova Scotia");
            groundAdvertisement.setSize("100 mts");
            groundAdvertisement.setSeatingCapacity("100");
            groundAdvertisement.setRentalCost(2000);
            groundAdvertisement.setCity("Halifax");
            groundAdvertisement.setStartDate(Date.valueOf("2021-03-23"));
            groundAdvertisement.setEndDate(Date.valueOf("2025-03-23"));
            groundAdvertisement.setSportsType("Cricket");
        }
        return groundAdvertisement;
    }

    public InputStream getGroundImage(String groundId)
    {
        File groundImage = new File("src/main/resources/test/yoga-mat-500x500.jpg");
        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(groundImage);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return inputStream;
    }
}

