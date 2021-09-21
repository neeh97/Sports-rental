package com.group1.sports_rental.UserLoginTest;

import com.group1.sports_rental.UserAuthentication.IUserLoginDao;

public class UserLoginDaoMock implements IUserLoginDao
{
    private final String existedEmail = "group1@gmail.com";
    private final String existedPassword = "123456789";
    private final long userId = 4;

    public Long login(String email,String password)
    {
       if((email.equals(existedEmail)) &&(password.equals(existedPassword)))
        {
            return userId;
        }
        else
       {
           return 0L;
       }
    }
}
