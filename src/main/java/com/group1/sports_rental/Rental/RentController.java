package com.group1.sports_rental.Rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

@Controller
public class RentController
{
   @Autowired
   RentDao rentProductDao;
   @Autowired
   RentDao rentGroundDao;
   Rental rental = Rental.instance();
   IRentService rentService = RentService.instance();
   private final String productUniqueString = "pr";

    @RequestMapping("/rent/{rentedItemId}")
    public String showDate(@PathVariable(name="rentedItemId") String rentedItemId, Model model, HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            model.addAttribute("rentedItemId", rentedItemId);
            model.addAttribute("rental", rental);
            return "rentSlots";
        }
    }

    @RequestMapping("/rentCalculation/{rentedItemId}")
    public String calculateRent(@PathVariable(name="rentedItemId") String rentedItemId,Rental rental, Date startDate, Date endDate, Model model,HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        Long userId = (Long) httpSession.getAttribute("userId");
        Double rentAmount = 0d;
        Boolean isAvailable = false;
        Boolean isSufficientCredits = false;
        if (rentedItemId.contains(productUniqueString))
        {
            rentAmount = rentService.calculateRent(rentedItemId, startDate, endDate, rentProductDao);
            isAvailable = rentService.checkAvailability(startDate, endDate, rentedItemId, rentProductDao);
            isSufficientCredits = rentService.checkCredits(userId,rentProductDao);
        }
        else
        {
            rentAmount = rentService.calculateRent(rentedItemId, startDate, endDate, rentGroundDao);
            isAvailable = rentService.checkAvailability(startDate, endDate, rentedItemId, rentGroundDao);
            isSufficientCredits = rentService.checkCredits(userId,rentGroundDao);
        }
        if(httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            if( rentAmount >0 && isAvailable && isSufficientCredits)
            {
                model.addAttribute("rentAmount", rentAmount);
                model.addAttribute("startDate", startDate);
                model.addAttribute("endDate", endDate);
                model.addAttribute("userId", userId);
                return "calculateRent";
            }
            else
            {
                model.addAttribute("invalidDates",rentAmount);
                model.addAttribute("isAvailable",isAvailable);
                model.addAttribute("isSufficientCredits",isSufficientCredits);
                return "rentSlots";
            }
        }

    }

    @PostMapping(value="/upload/rent/{rentedItemId}/{startDate}/{endDate}")
    public String insertRentDetails(@PathVariable(name="rentedItemId") String rentedItemId,@PathVariable(name="startDate") Date startDate, @PathVariable(name="endDate")Date endDate,HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        Long userId = (Long) httpSession.getAttribute("userId");
        if (rentedItemId.contains(productUniqueString))
        {
            rentService.insertRentalDetails(startDate, endDate, rentedItemId, userId, rentProductDao);
        }
        else
        {
            rentService.insertRentalDetails(startDate, endDate, rentedItemId, userId, rentGroundDao);
        }
        return "confirmation";
    }
}
