package com.group1.sports_rental.Advertisement.EventAdvertisement;

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
public class EventAdvertisementController
{
    IEventAdvertisementDao eventAdvertisementDao = EventAdvertisementDao.instance();
    IEventAdvertisementService eventAdvertisementService = new EventAdvertisementService();

    @RequestMapping("/upload/event")
    public String eventForm(Model model, HttpServletRequest httpServletRequest)
    {
        AbstractFactory factory = new ConcreteFactory();
        EventAdvertisement eventAdvertisement  = factory.createEvent();
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("login") == null)
        {
            return "home";
        }
        else
        {
            Long userId = (Long) httpSession.getAttribute("userId");
            model.addAttribute("userId", userId);
            model.addAttribute("eventAdvertisement", eventAdvertisement);
            return "uploadEvent";
        }
    }

    @PostMapping(value="/upload/event",consumes = {"multipart/form-data"})
    public String eventFormSubmission(@Valid EventAdvertisement eventAdvertisement, Model model, @RequestParam("imageUpload") MultipartFile imageUpload, HttpServletRequest httpServletRequest) throws IOException
    {
        Boolean isTicketPriceValid = eventAdvertisement.validateTicketPrice();
        Integer isEventDateValid = eventAdvertisement.validateEventDate();
        Boolean isImageUploaded = eventAdvertisement.validateImageUpload(imageUpload);
        Boolean isCityExistsInState = eventAdvertisement.validateCity(eventAdvertisementDao);
        Boolean isAddressValid = eventAdvertisement.validateEventAddress();
        Boolean isEventNameExist = eventAdvertisement.validateEventName();
        Integer isEventTimeValid = eventAdvertisement.validateEventTimeRange();
        Boolean isTotalTicketsValid  = eventAdvertisement.validateTotalTickets();
        HttpSession httpSession = httpServletRequest.getSession();
        Long userId = (Long) httpSession.getAttribute("userId");
        if (isTicketPriceValid && isImageUploaded && isCityExistsInState && isEventDateValid == 1 && isAddressValid && isEventNameExist && isEventTimeValid == -1 && isTotalTicketsValid)
        {
            model.addAttribute("eventAdvertisement", eventAdvertisement);
            model.addAttribute("userId", userId);
            eventAdvertisementService.insertEventRecord(eventAdvertisement,eventAdvertisementDao);
            return "eventFormSubmit";
        }
        else
        {
            model.addAttribute("userId", userId);
            model.addAttribute("isEventNameExist", isEventNameExist);
            model.addAttribute("isAddressValid", isAddressValid);
            model.addAttribute("isTicketPriceValid", isTicketPriceValid);
            model.addAttribute("isEventDateValid", isEventDateValid);
            model.addAttribute("imageUploadInvalid", isImageUploaded);
            model.addAttribute("isCityExistsInState", isCityExistsInState);
            model.addAttribute("isEventTimeValid", isEventTimeValid);
            model.addAttribute("isTotalTicketsValid", isTotalTicketsValid);
            return "uploadEvent";
        }
    }
}
