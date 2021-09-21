package com.group1.sports_rental.ProductSearch;

import com.group1.sports_rental.Advertisement.ProductAdvertisement.IProductAdvertisementDao;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisementDao;
import com.group1.sports_rental.UserRegistration.UserRegistration;
import com.group1.sports_rental.UserRegistration.UserRegistrationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductSearchController
{

    @Autowired
    IProductAdvertisementDao productAdvertisementDao = ProductAdvertisementDao.instance();
    UserRegistrationDao userRegistrationDao = new UserRegistrationDao();
    IProductSearchService productSearchService = ProductSearchService.instance();


    @RequestMapping("/search")
    public String searchPage(Model model, HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession == null || httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        Long userId = (Long) httpSession.getAttribute("userId");
        UserRegistration user = userRegistrationDao.findOne(userId);
        if (user == null)
        {
            return "errorPage";
        }
        String city = user.getCity();
        if (city == null)
        {
            return "errorPage";
        }
        try
        {
            List<ProductAdvertisement> defaultSearchList = productSearchService.setDefaultSearchPage(city, productAdvertisementDao);
            model.addAttribute("products", defaultSearchList);
            model.addAttribute("SearchFilter", new ProductSearchFilter());
        }
        catch (Exception e) {
            return "errorPage";
        }

        return "searchPage";
    }

    @RequestMapping(value = "/search/text")
    public String searchText(@RequestParam(value = "searchText", required = false) String searchText, Model model, HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession == null || httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        Long userId = (Long) httpSession.getAttribute("userId");
        UserRegistration user = userRegistrationDao.findOne(userId);
        if (user == null)
        {
            return "errorPage";
        }
        String city = user.getCity();
        if (city == null)
        {
            return "errorPage";
        }
        try
        {
            List<ProductAdvertisement> searchProductList = productSearchService.searchProducts(searchText, city, productAdvertisementDao);
            model.addAttribute("products", searchProductList);
            model.addAttribute("SearchFilter", new ProductSearchFilter());
        }
        catch (Exception e)
        {
            return "errorPage";
        }
        return "searchPage";
    }

    @PostMapping(value = "/search/filters")
    public String applyFilters(@ModelAttribute("SearchFilter") ProductSearchFilter productSearchFilter, Model model, HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession == null || httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        Long userId = (Long) httpSession.getAttribute("userId");
        UserRegistration user = userRegistrationDao.findOne(userId);
        if (user == null)
        {
            return "errorPage";
        }
        String city = user.getCity();
        if (city == null)
        {
            return "errorPage";
        }
        try
        {
            model.addAttribute("products", productSearchFilter.applyFilters(city, productAdvertisementDao));
        }
        catch (Exception e) {
            return "errorPage";
        }
        return "searchPage";
    }
}
