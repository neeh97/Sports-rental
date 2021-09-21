package com.group1.sports_rental.UserRegistrationTest;

import com.group1.sports_rental.UserRegistration.IUserRegistrationDao;
import com.group1.sports_rental.UserRegistration.UserRegistration;

public class UserRegistrationDaoMock implements IUserRegistrationDao
{

    public void register(UserRegistration userRegistration)
    {
        userRegistration.setEmail("group1@gmail.com");
        userRegistration.setPassword("Group1@123");
        userRegistration.setConfirmPassword("Group1@123");
        userRegistration.setFirstName("Group");
        userRegistration.setLastName("Project");
        userRegistration.setPhoneNumber(9876543210L);
        userRegistration.setCity("Halifax");
        userRegistration.setAddress("Dalhousie,Novascotia");
    }

    public Boolean findByEmail(String email)
    {
        if(email.equals("group1@gmail.com"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public UserRegistration findOne(Long userId)
    {
        UserRegistration userRegistration = UserRegistration.instance();
        if(userId == 4)
        {
            userRegistration.setEmail("group1@gmail.com");
            userRegistration.setPassword("Group1@123");
            userRegistration.setConfirmPassword("Group1@123");
            userRegistration.setFirstName("Group");
            userRegistration.setLastName("Project");
            userRegistration.setPhoneNumber(9876543210L);
            userRegistration.setCity("Halifax");
            userRegistration.setAddress("Dalhousie,Novascotia");
        }
        return userRegistration;
    }
}
