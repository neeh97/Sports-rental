package com.group1.sports_rental.Advertisement.GroundAdvertisement;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class GroundAdvertisementController
{
    IGroundAdvertisementDao groundAdvertisementDao = new GroundAdvertisementDao();
    IGroundAdvertisementService groundAdvertisementService = new GroundAdvertisementService();

    @RequestMapping("/upload/ground")
    public String groundForm(Model model, HttpServletRequest httpServletRequest)
    {
        AbstractFactory factory = new ConcreteFactory();
        GroundAdvertisement groundAdvertisement  = factory.createGround();
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            Long userId = (Long) httpSession.getAttribute("userId");
            model.addAttribute("userId", userId);
            model.addAttribute("groundAdvertisement", groundAdvertisement);
            return "uploadGround";
        }
    }
    
    @PostMapping(value="/upload/ground",consumes = {"multipart/form-data"})
    public String groundSubmission(@Valid GroundAdvertisement groundAdvertisement, Model model, @RequestParam("imageUpload") MultipartFile imageUpload, HttpServletRequest httpServletRequest) throws IOException
    {
        Boolean isRentalCostValid = groundAdvertisement.validateRentalCost();
        Integer isDateRangeValidate = groundAdvertisement.validateDateRange();
        Boolean isImageUploaded = groundAdvertisement.validateImageUpload(imageUpload);
        Boolean isCityExistsInState = groundAdvertisement.validateCityExists(groundAdvertisementDao);
        Boolean isAddressValid = groundAdvertisement.validateAddress();
        Boolean isGroundNameExist = groundAdvertisement.validateGroundName();
        HttpSession httpSession = httpServletRequest.getSession();
        Long userId = (Long) httpSession.getAttribute("userId");
        if (isRentalCostValid && isImageUploaded && isCityExistsInState && isDateRangeValidate == -1 && isAddressValid && isGroundNameExist )
        {
            model.addAttribute("groundAdvertisement", groundAdvertisement);
            model.addAttribute("userId", userId);
            groundAdvertisementService.insertGroundData(groundAdvertisement,groundAdvertisementDao);
            return "groundFormSubmit";
        }
        else
        {
            model.addAttribute("userId", userId);
            model.addAttribute("groundNameInvalid", isGroundNameExist);
            model.addAttribute("addressInvalid", isAddressValid);
            model.addAttribute("rentalCostInvalid", isRentalCostValid);
            model.addAttribute("dateRangeInvalid", isDateRangeValidate);
            model.addAttribute("imageUploadInvalid", isImageUploaded);
            model.addAttribute("cityInvalid", isCityExistsInState);
            return "uploadGround";
        }
    }
}
