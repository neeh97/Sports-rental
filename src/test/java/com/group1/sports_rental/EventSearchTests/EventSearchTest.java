package com.group1.sports_rental.EventSearchTests;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import com.group1.sports_rental.Advertisement.EventAdvertisement.IEventAdvertisementDao;
import com.group1.sports_rental.AdvertisementTest.EventAdvertisementTest.EventAdvertisementDaoMock;
import com.group1.sports_rental.EventSearch.EventSearchFilter;
import com.group1.sports_rental.EventSearch.EventSearchService;
import com.group1.sports_rental.EventSearch.IEventSearchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EventSearchTest {

    @Test
    public void eventSearchDefaultTest()
    {
        IEventSearchService eventSearchService = EventSearchService.instance();
        IEventAdvertisementDao eventAdvertisementDao = new EventAdvertisementDaoMock();
        List<EventAdvertisement> eventAdvertisementList = eventSearchService.getDefaultSearchList("Scranton", eventAdvertisementDao);
        Assertions.assertEquals("Roast of Michael Scott", eventAdvertisementList.get(0).getEventName());
    }

    @Test
    public void emptyEventSearchDefaultTest()
    {
        IEventSearchService eventSearchService = EventSearchService.instance();
        IEventAdvertisementDao eventAdvertisementDao = new EventAdvertisementDaoMock();
        List<EventAdvertisement> eventAdvertisementList = eventSearchService.getDefaultSearchList("Halifax", eventAdvertisementDao);
        Assertions.assertEquals(0, eventAdvertisementList.size());
    }

    @Test
    public void searchEventNameTest()
    {
        IEventSearchService eventSearchService = EventSearchService.instance();
        IEventAdvertisementDao eventAdvertisementDao = new EventAdvertisementDaoMock();
        List<EventAdvertisement> eventAdvertisementList = eventSearchService.searchEventName("Battle", "Hornburg", eventAdvertisementDao);
        Assertions.assertEquals("Battle of Helm's Deep", eventAdvertisementList.get(0).getEventName());
    }

    @Test
    public void emptyEventSearchNameTest()
    {
        IEventSearchService eventSearchService = EventSearchService.instance();
        IEventAdvertisementDao eventAdvertisementDao = new EventAdvertisementDaoMock();
        List<EventAdvertisement> eventAdvertisementList = eventSearchService.searchEventName("Roast", "Halifax", eventAdvertisementDao);
        Assertions.assertEquals(0, eventAdvertisementList.size());
    }

    @Test
    public void applyFiltersTest()
    {
        EventSearchFilter eventSearchFilter = new EventSearchFilter();
        IEventAdvertisementDao eventAdvertisementDao = new EventAdvertisementDaoMock();
        eventSearchFilter.setLocation("Shiganshina");
        List<EventAdvertisement> eventAdvertisementList = eventSearchFilter.applyFilters("Shiganshina", eventAdvertisementDao);
        Assertions.assertEquals("Rumbling", eventAdvertisementList.get(0).getEventName());
    }

    @Test
    public void emptyApplyFiltersTest()
    {
        EventSearchFilter eventSearchFilter = new EventSearchFilter();
        IEventAdvertisementDao eventAdvertisementDao = new EventAdvertisementDaoMock();
        eventSearchFilter.setLocation("Marley");
        List<EventAdvertisement> eventAdvertisementList = eventSearchFilter.applyFilters("Shiganshina", eventAdvertisementDao);
        Assertions.assertEquals(0, eventAdvertisementList.size());
    }

    @Test
    public void applyMinimumPriceFilterTest()
    {
        EventSearchFilter eventSearchFilter = new EventSearchFilter();
        IEventAdvertisementDao eventAdvertisementDao = new EventAdvertisementDaoMock();
        eventSearchFilter.setMinimumPrice("100");
        List<EventAdvertisement> eventAdvertisementList = eventSearchFilter.applyFilters("Shiganshina", eventAdvertisementDao);
        Assertions.assertEquals("Queen Concert", eventAdvertisementList.get(0).getEventName());
    }

    @Test
    public void emptyMinimumPriceFilterTest()
    {
        EventSearchFilter eventSearchFilter = new EventSearchFilter();
        IEventAdvertisementDao eventAdvertisementDao = new EventAdvertisementDaoMock();
        eventSearchFilter.setMinimumPrice("1000");
        List<EventAdvertisement> eventAdvertisementList = eventSearchFilter.applyFilters("Shiganshina", eventAdvertisementDao);
        Assertions.assertEquals(0, eventAdvertisementList.size());
    }

    @Test
    public void applyMaximumPriceFilterTest()
    {
        EventSearchFilter eventSearchFilter = new EventSearchFilter();
        IEventAdvertisementDao eventAdvertisementDao = new EventAdvertisementDaoMock();
        eventSearchFilter.setMaximumPrice("200");
        List<EventAdvertisement> eventAdvertisementList = eventSearchFilter.applyFilters("Shiganshina", eventAdvertisementDao);
        Assertions.assertEquals("Joy Division Concert", eventAdvertisementList.get(0).getEventName());
    }

    @Test
    public void emptyMaximumPriceFilterTest()
    {
        EventSearchFilter eventSearchFilter = new EventSearchFilter();
        IEventAdvertisementDao eventAdvertisementDao = new EventAdvertisementDaoMock();
        eventSearchFilter.setMaximumPrice("20");
        List<EventAdvertisement> eventAdvertisementList = eventSearchFilter.applyFilters("Shiganshina", eventAdvertisementDao);
        Assertions.assertEquals(0, eventAdvertisementList.size());
    }
}
