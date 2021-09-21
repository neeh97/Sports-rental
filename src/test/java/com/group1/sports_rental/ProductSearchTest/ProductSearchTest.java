package com.group1.sports_rental.ProductSearchTest;

import com.group1.sports_rental.Advertisement.ProductAdvertisement.IProductAdvertisementDao;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import com.group1.sports_rental.AdvertisementTest.ProductAdvertisement.ProductAdvertisementDaoMock;
import com.group1.sports_rental.ProductSearch.IProductSearchService;
import com.group1.sports_rental.ProductSearch.ProductSearchFilter;
import com.group1.sports_rental.ProductSearch.ProductSearchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductSearchTest {

    @Test
    public void searchProductsTest()
    {
        IProductSearchService productSearchService = ProductSearchService.instance();
        IProductAdvertisementDao productAdvertisementDaoMock = new ProductAdvertisementDaoMock();
        List<ProductAdvertisement> defaultList = productSearchService.searchProducts("Yoga", "Halifax", productAdvertisementDaoMock);
        Assertions.assertEquals("Yoga mat", defaultList.get(0).getProductName());
    }

    @Test
    public void emptySearchResultTest()
    {
        IProductSearchService productSearchService = ProductSearchService.instance();
        IProductAdvertisementDao productAdvertisementDaoMock = new ProductAdvertisementDaoMock();
        List<ProductAdvertisement> defaultList = productSearchService.searchProducts("Cricket", "Halifax", productAdvertisementDaoMock);
        Assertions.assertEquals(0, defaultList.size());
    }

    @Test
    public void defaultSearchProductsTest()
    {
        IProductSearchService productSearchService = ProductSearchService.instance();
        IProductAdvertisementDao productAdvertisementDaoMock = new ProductAdvertisementDaoMock();
        List<ProductAdvertisement> defaultList = productSearchService.setDefaultSearchPage("Halifax", productAdvertisementDaoMock);
        Assertions.assertEquals("Yoga mat", defaultList.get(0).getProductName());
    }

    @Test
    public void emptyDefaultSearchProductsTest()
    {
        IProductSearchService productSearchService = ProductSearchService.instance();
        IProductAdvertisementDao productAdvertisementDaoMock = new ProductAdvertisementDaoMock();
        List<ProductAdvertisement> defaultList = productSearchService.setDefaultSearchPage("Alberta", productAdvertisementDaoMock);
        Assertions.assertEquals(0, defaultList.size());
    }

    @Test
    public void applyFiltersLocationTest()
    {
        ProductSearchFilter productSearchFilter = new ProductSearchFilter();
        IProductAdvertisementDao productAdvertisementDao = new ProductAdvertisementDaoMock();
        productSearchFilter.setLocation("Ameerpet");
        List<ProductAdvertisement> locationList = productSearchFilter.applyFilters("Hyderabad", productAdvertisementDao);
        Assertions.assertEquals("Yoga mat", locationList.get(0).getProductName());
    }

    @Test
    public void applyFiltersCategoryTest()
    {
        ProductSearchFilter productSearchFilter = new ProductSearchFilter();
        IProductAdvertisementDao productAdvertisementDao = new ProductAdvertisementDaoMock();
        productSearchFilter.setCategory("equipment");
        List<ProductAdvertisement> categoryList = productSearchFilter.applyFilters("Alberta", productAdvertisementDao);
        Assertions.assertEquals("Cricket bat", categoryList.get(0).getProductName());
    }

    @Test
    public void applyFiltersSportsTypeTest()
    {
        ProductSearchFilter productSearchFilter = new ProductSearchFilter();
        IProductAdvertisementDao productAdvertisementDao = new ProductAdvertisementDaoMock();
        productSearchFilter.setSportsType("Football");
        List<ProductAdvertisement> sportsTypeList = productSearchFilter.applyFilters("London", productAdvertisementDao);
        Assertions.assertEquals("Nivea Football", sportsTypeList.get(0).getProductName());
    }

    @Test
    public void emptyApplyFiltersLocationTest()
    {
        ProductSearchFilter productSearchFilter = new ProductSearchFilter();
        IProductAdvertisementDao productAdvertisementDao = new ProductAdvertisementDaoMock();
        productSearchFilter.setLocation("Begumpet");
        List<ProductAdvertisement> locationList = productSearchFilter.applyFilters("Tokyo", productAdvertisementDao);
        Assertions.assertEquals(0, locationList.size());
    }

    @Test
    public void emptyApplyFiltersCategoryTest()
    {
        ProductSearchFilter productSearchFilter = new ProductSearchFilter();
        IProductAdvertisementDao productAdvertisementDao = new ProductAdvertisementDaoMock();
        productSearchFilter.setCategory("clothing");
        List<ProductAdvertisement> categoryList = productSearchFilter.applyFilters("Amsterdam", productAdvertisementDao);
        Assertions.assertEquals(0, categoryList.size());
    }

    @Test
    public void emptyApplyFiltersSportsTypeTest()
    {
        ProductSearchFilter productSearchFilter = new ProductSearchFilter();
        IProductAdvertisementDao productAdvertisementDao = new ProductAdvertisementDaoMock();
        productSearchFilter.setSportsType("Basketball");
        List<ProductAdvertisement> sportsTypeList = productSearchFilter.applyFilters( "Munich", productAdvertisementDao);
        Assertions.assertEquals(0, sportsTypeList.size());
    }
}
