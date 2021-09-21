package com.group1.sports_rental.GroundSearchTests;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisementDao;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.IGroundAdvertisementDao;
import com.group1.sports_rental.AdvertisementTest.GroundAdvertisement.GroundAdvertisementDaoMock;
import com.group1.sports_rental.GroundSearch.GroundSearchFilter;
import com.group1.sports_rental.GroundSearch.GroundSearchService;
import com.group1.sports_rental.GroundSearch.IGroundSearchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GroundSearchTest {

    @Test
    public void defaultGroundSearchTest()
    {
        IGroundSearchService groundSearchService = GroundSearchService.instance();
        IGroundAdvertisementDao groundAdvertisementDao = new GroundAdvertisementDaoMock();
        List<GroundAdvertisement> groundAdvertisementList = groundSearchService.setDefaultGroundsList("Halifax", groundAdvertisementDao);
        Assertions.assertEquals("HFX sports bar & grill", groundAdvertisementList.get(0).getGroundName());
    }

    @Test
    public void emptyDefaultGroundSearchTest()
    {
        IGroundSearchService groundSearchService = GroundSearchService.instance();
        IGroundAdvertisementDao groundAdvertisementDao = new GroundAdvertisementDaoMock();
        List<GroundAdvertisement> groundAdvertisementList = groundSearchService.setDefaultGroundsList("Toronto", groundAdvertisementDao);
        Assertions.assertEquals(0, groundAdvertisementList.size());
    }

    @Test
    public void groundSearchNameTest()
    {
        IGroundSearchService groundSearchService = GroundSearchService.instance();
        IGroundAdvertisementDao groundAdvertisementDao = new GroundAdvertisementDaoMock();
        List<GroundAdvertisement> groundAdvertisementList = groundSearchService.searchGroundText("HFX", "Halifax", groundAdvertisementDao);
        Assertions.assertEquals("HFX sports bar & grill", groundAdvertisementList.get(0).getGroundName());
    }

    @Test
    public void emptyGroundSearchNameTest()
    {
        IGroundSearchService groundSearchService = GroundSearchService.instance();
        IGroundAdvertisementDao groundAdvertisementDao = new GroundAdvertisementDaoMock();
        List<GroundAdvertisement> groundAdvertisementList = groundSearchService.searchGroundText("HFS", "Halifax", groundAdvertisementDao);
        Assertions.assertEquals(0, groundAdvertisementList.size());
    }

    @Test
    public void sportsTypeFilterTest()
    {
        GroundSearchFilter groundSearchFilter = new GroundSearchFilter();
        IGroundAdvertisementDao groundAdvertisementDao = new GroundAdvertisementDaoMock();
        groundSearchFilter.setSportsType("Skating");
        List<GroundAdvertisement> groundAdvertisementList = groundSearchFilter.applyFilters("Halifax", groundAdvertisementDao);
        Assertions.assertEquals("Gymkhana grounds", groundAdvertisementList.get(0).getGroundName());
    }

    @Test
    public void emptySportsTypeFilterTest()
    {
        GroundSearchFilter groundSearchFilter = new GroundSearchFilter();
        IGroundAdvertisementDao groundAdvertisementDao = new GroundAdvertisementDaoMock();
        groundSearchFilter.setSportsType("Football");
        List<GroundAdvertisement> groundAdvertisementList = groundSearchFilter.applyFilters("Halifax", groundAdvertisementDao);
        Assertions.assertEquals(0, groundAdvertisementList.size());
    }

    @Test
    public void locationFilterTest()
    {
        GroundSearchFilter groundSearchFilter = new GroundSearchFilter();
        IGroundAdvertisementDao groundAdvertisementDao = new GroundAdvertisementDaoMock();
        groundSearchFilter.setLocation("Lawrence St");
        List<GroundAdvertisement> groundAdvertisementList = groundSearchFilter.applyFilters("Halifax", groundAdvertisementDao);
        Assertions.assertEquals("HFX sports bar & grill", groundAdvertisementList.get(0).getGroundName());
    }

    @Test
    public void emptyLocationFilterTest()
    {
        GroundSearchFilter groundSearchFilter = new GroundSearchFilter();
        IGroundAdvertisementDao groundAdvertisementDao = new GroundAdvertisementDaoMock();
        groundSearchFilter.setLocation("Dalhousie University");
        List<GroundAdvertisement> groundAdvertisementList = groundSearchFilter.applyFilters("Halifax", groundAdvertisementDao);
        Assertions.assertEquals(0, groundAdvertisementList.size());
    }

}
