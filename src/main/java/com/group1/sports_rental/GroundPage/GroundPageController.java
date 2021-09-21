package com.group1.sports_rental.GroundPage;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
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
public class GroundPageController
{
    @Autowired
    IGroundPageDao groundPageDao;
    IGroundPageService groundPageService = GroundPageService.instance();

    @RequestMapping(value="/getGround/{groundId}",produces = MediaType.IMAGE_PNG_VALUE)
    public String groundPage(@PathVariable(name="groundId") String groundId, Model model, HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            GroundAdvertisement groundAdvertisement;
            groundAdvertisement = groundPageService.displayGround(groundId,groundPageDao);
            model.addAttribute("groundAdvertisement", groundAdvertisement);
            model.addAttribute("groundId", groundId);
            return "groundDisplayPage";
        }
    }

    @RequestMapping(value = "/getGroundImage/{id}", method = RequestMethod.GET)
    public void displayImage(@PathVariable("id") String groundId, HttpServletResponse httpServletResponse) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = groundPageService.displayGroundImage(groundId,groundPageDao);
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
            exception.printStackTrace();
        }
    }
}
