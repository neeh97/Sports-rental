package com.group1.sports_rental.UserAuthentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserLogoutController
{
    @RequestMapping("/logout")
    public String logout(Model model, HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.invalidate();
        return "home";
    }
}
