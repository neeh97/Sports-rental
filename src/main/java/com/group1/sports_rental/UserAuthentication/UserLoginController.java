package com.group1.sports_rental.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class UserLoginController
{
    @Autowired
    IUserLoginDao userLoginDao;
    IUserLoginService userLoginService = UserLoginService.instance();

    @RequestMapping("/login")
    public String showNewForm(Model model)
    {
            UserLogin userLogin =UserLogin.instance();
            model.addAttribute("userLogin", userLogin);
            return "login";
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public String processLogin(@Valid UserLogin userLogin, Model model, HttpServletRequest httpServletRequest ) throws SQLException
    {
        String email = userLogin.getEmail();
        String password = userLogin.getPassword();
        Long userId =userLoginService.login(userLoginDao,email,password);
        if (userId==0)
        {
            model.addAttribute("invalidLogin",userId);
            return "login";
        }
        else
            {
                HttpSession httpSession = httpServletRequest.getSession();
                httpSession.setAttribute("userId",userId);
                httpSession.setAttribute("login","yes");
                return "checkIn";
            }

    }

}

