package com.group1.sports_rental.Advertisement.ProductAdvertisement;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
public class ProductAdvertisementController
{
    IProductAdvertisementDao productAdvertisementDao = ProductAdvertisementDao.instance();
    IProductAdvertisementService productAdvertisementService = new ProductAdvertisementService();

    @RequestMapping("/upload/product")
    public String productForm(Model model, HttpServletRequest httpServletRequest)
    {
        AbstractFactory factory = new ConcreteFactory();
        ProductAdvertisement productAdvertisement = factory.createProduct();
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            Long userId = (Long) httpSession.getAttribute("userId");
            model.addAttribute("userId", userId);
            model.addAttribute("productAdvertisement", productAdvertisement);
            return "uploading";
        }
    }

    @PostMapping(value="/upload/product",consumes = {"multipart/form-data"})
    public String productSubmission(@Valid ProductAdvertisement productAdvertisement, Model model, @RequestParam("imageUpload") MultipartFile imageUpload, HttpServletRequest httpServletRequest) throws IOException
    {
        Boolean productValidationBool = productAdvertisement.validateProductName();
        Boolean productDescriptionBool = productAdvertisement.validateProductDescription();
        Boolean rentalCostBool = productAdvertisement.validateRentalCost();
        Integer dateRangeValidate = productAdvertisement.validateDateRange();
        Boolean imageUploadBool = productAdvertisement.validateImageUpload(imageUpload);
        HttpSession httpSession = httpServletRequest.getSession();
        Long userId = (Long) httpSession.getAttribute("userId");
        if (productValidationBool && productDescriptionBool && rentalCostBool && dateRangeValidate == -1 && imageUploadBool)
        {
            model.addAttribute("productAdvertisement", productAdvertisement);
            model.addAttribute("userId", userId);
            productAdvertisementService.insertProductData(productAdvertisement,productAdvertisementDao);
            return "formSubmit";
        }
        else
        {
            model.addAttribute("userId", userId);
            model.addAttribute("productNameInvalid", productValidationBool);
            model.addAttribute("productDescriptionInvalid", productDescriptionBool);
            model.addAttribute("rentalCostInvalid", rentalCostBool);
            model.addAttribute("dateRangeInvalid", dateRangeValidate);
            model.addAttribute("imageUploadInvalid", imageUploadBool);
            return "uploading";
        }
    }
}
