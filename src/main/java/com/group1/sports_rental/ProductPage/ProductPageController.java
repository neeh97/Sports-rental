package com.group1.sports_rental.ProductPage;

import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;

@Controller
public class ProductPageController
{
    @Autowired
    IProductPageDao productPageDao;
    IProductPageService productPageService = ProductPageService.instance();

    @RequestMapping(value="/getProduct/{productId}",produces = MediaType.IMAGE_PNG_VALUE)
    public String productPage(@PathVariable(name="productId") String productId, Model model, HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            ProductAdvertisement productAdvertisement;
            productAdvertisement = productPageService.displayProduct(productId,productPageDao);
            model.addAttribute("productAdvertisement", productAdvertisement);
            model.addAttribute("productId", productId);
            return "productDisplayPage";
        }
    }

    @RequestMapping(value = "/getProductImage/{id}", method = RequestMethod.GET)
    public void displayImage(@PathVariable("id") String productId, HttpServletResponse httpServletResponse) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = productPageService.displayProductImage(productId,productPageDao);
            if(byteArrayOutputStream == null)
            {
               return;
            }
            httpServletResponse.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            httpServletResponse.getOutputStream().write(byteArrayOutputStream.toByteArray());
            httpServletResponse.getOutputStream().close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();;
        }
    }
}
