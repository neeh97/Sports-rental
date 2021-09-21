package com.group1.sports_rental.EventSearch;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisementDao;
import com.group1.sports_rental.Advertisement.EventAdvertisement.IEventAdvertisementDao;
import com.group1.sports_rental.UserRegistration.IUserRegistrationDao;
import com.group1.sports_rental.UserRegistration.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class EventSearchController {

    @Autowired
    IUserRegistrationDao userRegistrationDao;
    IEventSearchService eventSearch = EventSearchService.instance();
    IEventAdvertisementDao eventAdvertisementDao = EventAdvertisementDao.instance();

    @RequestMapping(value = "/searchEvents")
    public String searchEvents(Model model, HttpServletRequest httpServletRequest)
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
        model.addAttribute("events", eventSearch.getDefaultSearchList(user.getCity(), eventAdvertisementDao));
        model.addAttribute("EventSearchFilter", new EventSearchFilter());
        return "searchEvent";
    }

    @RequestMapping(value = "/searchEvents/text")
    public String searchEventName(@RequestParam("searchText") String searchText, Model model, HttpServletRequest httpServletRequest)
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
        model.addAttribute("events", eventSearch.searchEventName(searchText, user.getCity(), eventAdvertisementDao));
        model.addAttribute("EventSearchFilter", new EventSearchFilter());
        return "searchEvent";
    }

    @PostMapping(value = "/searchEvents/filters")
    public String applyFilters(@ModelAttribute("EventSearchFilter") EventSearchFilter eventSearchFilter, Model model, HttpServletRequest httpServletRequest)
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
        model.addAttribute("events", eventSearchFilter.applyFilters(user.getCity(), eventAdvertisementDao));
        return "searchEvent";
    }
}
