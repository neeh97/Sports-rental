package com.group1.sports_rental.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
public class UserRegistrationController
{
    @Autowired
    private IUserRegistrationDao userRegistrationDao;
    private IUserRegistrationService userRegistrationService = UserRegistrationService.instance();

    @RequestMapping("/form")
    public String showNewForm(Model model)
    {
        UserRegistration userRegistration =UserRegistration.instance();
        model.addAttribute("userRegistration", userRegistration);
        return "register";
    }

    @RequestMapping("/save")
    public String register(@Valid UserRegistration userRegistration, Model model)
    {
        Boolean isValidEmail = userRegistration.isValidEmail();
        Boolean isValidPassword = userRegistration.isValidPassword();
        Boolean isValidConfirmPassword = userRegistration.isValidConfirmPassword();
        Boolean isValidFirstName = userRegistration.isValidFirstName();
        Boolean isValidLastName = userRegistration.isValidLastName();
        Boolean isExistedUser = userRegistrationService.findByEmail(userRegistration.getEmail(),userRegistrationDao);
        if(isValidEmail && isValidPassword && isValidConfirmPassword && isValidFirstName && isValidLastName && isExistedUser)
        {
            model.addAttribute("userRegistration", userRegistration);
            userRegistrationService.register(userRegistrationDao,userRegistration);
            return "registrationSuccess";
        }
        else
        {
            model.addAttribute("invalidUser",isExistedUser);
            model.addAttribute("invalidEmail",isValidEmail);
            model.addAttribute("invalidPassword",isValidPassword);
            model.addAttribute("invalidConfirmPassword",isValidConfirmPassword);
            model.addAttribute("invalidFirstName",isValidFirstName);
            model.addAttribute("invalidLastName",isValidLastName);
            return "register";
        }
    }
}
