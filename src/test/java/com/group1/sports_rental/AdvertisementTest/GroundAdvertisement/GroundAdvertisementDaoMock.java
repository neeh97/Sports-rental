package com.group1.sports_rental.AdvertisementTest.GroundAdvertisement;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.IGroundAdvertisementDao;
import org.springframework.util.StringUtils;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GroundAdvertisementDaoMock implements IGroundAdvertisementDao
{
    private final static AbstractFactory factory = new ConcreteFactory();

    public String fetchCity(String state, String city)
    {
        String validCity = null;
        String stateLower = state.toLowerCase();
        String cityLower = city.toLowerCase();
        String citySpaceRemoved = cityLower.replace(" ","");
        if (citySpaceRemoved.equals("halifax") && stateLower.equals("nova scotia"))
        {
            validCity = city;
        }
        return validCity;
    }

    public void insertGround(GroundAdvertisement groundAdvertisement)
    {
        Date startDate=Date.valueOf("2021-04-04");
        Date endDate=Date.valueOf("2021-04-08");
        groundAdvertisement.setId("gr_abcdef12");
        groundAdvertisement.setAddress("1024 wellington street");
        groundAdvertisement.setSize("400");
        groundAdvertisement.setSeatingCapacity("500");
        groundAdvertisement.setRentalCost(22);
        groundAdvertisement.setStartDate(startDate);
        groundAdvertisement.setEndDate(endDate);
        groundAdvertisement.setCity("halifax");
        groundAdvertisement.setUserId(12L);
        groundAdvertisement.setState("Nova scotia");
        groundAdvertisement.setGroundName("Siri stadium");
        groundAdvertisement.setSportsType("cricket");
    }

    public List<GroundAdvertisement> getDefaultGrounds(String city)
    {
        List<GroundAdvertisement> groundAdvertisementList = new ArrayList<>();
        if (city.equals("Halifax"))
        {
            GroundAdvertisement groundAdvertisement = factory.createGround();
            groundAdvertisement.setId("124");
            groundAdvertisement.setGroundName("HFX sports bar & grill");
            groundAdvertisement.setSportsType("Bowling");
            groundAdvertisement.setAddress("Lawrence St");
            groundAdvertisementList.add(groundAdvertisement);
        }
        return groundAdvertisementList;
    }

    public List<GroundAdvertisement> searchGroundName(String city, String searchText)
    {
        List<GroundAdvertisement> groundAdvertisementList = new ArrayList<>();
        if (searchText.equals("HFX"))
        {
            GroundAdvertisement groundAdvertisement = factory.createGround();
            groundAdvertisement.setId("124");
            groundAdvertisement.setGroundName("HFX sports bar & grill");
            groundAdvertisement.setSportsType("Bowling");
            groundAdvertisement.setAddress("Lawrence St");
            groundAdvertisementList.add(groundAdvertisement);
        }
        return groundAdvertisementList;
    }

    public List<GroundAdvertisement> applyFilters(String city, String sportsType, String location)
    {
        List<GroundAdvertisement> groundAdvertisementList = new ArrayList<>();
        if (StringUtils.hasText(sportsType) && sportsType.equals("Skating"))
        {
            GroundAdvertisement groundAdvertisement = factory.createGround();
            groundAdvertisement.setId("123");
            groundAdvertisement.setGroundName("Gymkhana grounds");
            groundAdvertisement.setSize("1000");
            groundAdvertisement.setSportsType("Skating");
            groundAdvertisementList.add(groundAdvertisement);
        }
        if (StringUtils.hasText(location) && location.equals("Lawrence St"))
        {
            GroundAdvertisement groundAdvertisement = factory.createGround();
            groundAdvertisement.setId("124");
            groundAdvertisement.setGroundName("HFX sports bar & grill");
            groundAdvertisement.setSportsType("Bowling");
            groundAdvertisement.setAddress("Lawrence St");
            groundAdvertisementList.add(groundAdvertisement);
        }
        return groundAdvertisementList;
    }

    public GroundAdvertisement findOne(String groundId)
    {
        GroundAdvertisement groundAdvertisement = factory.createGround();
        groundAdvertisement.setId("124");
        groundAdvertisement.setGroundName("HFX sports bar & grill");
        groundAdvertisement.setSportsType("Bowling");
        groundAdvertisement.setAddress("Lawrence St");
        return groundAdvertisement;
    }
}
