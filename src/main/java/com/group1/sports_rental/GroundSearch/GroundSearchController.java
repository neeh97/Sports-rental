package com.group1.sports_rental.GroundSearch;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisementDao;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.IGroundAdvertisementDao;
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
public class GroundSearchController
{

    @Autowired
    IUserRegistrationDao userRegistrationDao;
    IGroundSearchService groundSearchService = GroundSearchService.instance();
    IGroundAdvertisementDao groundAdvertisementDao = GroundAdvertisementDao.instance();

    @RequestMapping("/searchGrounds")
    public String searchGrounds(Model model, HttpServletRequest httpServletRequest)
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
        model.addAttribute("grounds", groundSearchService.setDefaultGroundsList(user.getCity(), groundAdvertisementDao));
        model.addAttribute("GroundSearchFilter", new GroundSearchFilter());
        return "searchGround";
    }

    @RequestMapping("/searchGround/text")
    public String searchGroundsText(@RequestParam(value = "searchText", required = false) String searchText, Model model, HttpServletRequest httpServletRequest)
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
        model.addAttribute("grounds", groundSearchService.searchGroundText(searchText, user.getCity(), groundAdvertisementDao));
        model.addAttribute("GroundSearchFilter", new GroundSearchFilter());
        return "searchGround";
    }

    @PostMapping(value = "/searchGround/filters")
    public String applyFilters(@ModelAttribute("GroundSearchFilter") GroundSearchFilter groundSearchFilter, Model model, HttpServletRequest httpServletRequest)
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
        model.addAttribute("grounds", groundSearchFilter.applyFilters(user.getCity(), groundAdvertisementDao));
        return "searchGround";
    }
}
