package com.group1.sports_rental.EventPage;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
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
public class EventPageController
{
    IEventPageDao eventPageDao = new EventPageDao();
    IEventPageService eventPageService = new EventPageService();

    @RequestMapping(value="/getEvent/{eventId}",produces = MediaType.IMAGE_PNG_VALUE)
    public String productForm(@PathVariable(name="eventId") String eventId, Model model, HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            Long userId = (Long) httpSession.getAttribute("userId");
            EventAdvertisement eventAdvertisement = eventPageService.showEvent(eventId,eventPageDao);
            model.addAttribute("eventAdvertisement", eventAdvertisement);
            model.addAttribute("eventId", eventId);
            model.addAttribute("userId", userId);
            httpSession.setAttribute("userId",userId);
            return "eventDisplayPage";
        }
    }

    @RequestMapping(value = "/getEventImage/{id}", method = RequestMethod.GET)
    public void displayImage(@PathVariable("id") String eventId, HttpServletResponse httpServletResponse)
    {
        try
        {
            ByteArrayOutputStream byteArrayOutputStream = eventPageService.displayEventImage(eventId,eventPageDao);
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
