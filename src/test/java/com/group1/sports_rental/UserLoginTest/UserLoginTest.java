package com.group1.sports_rental.UserLoginTest;

import com.group1.sports_rental.UserAuthentication.IUserLoginService;
import com.group1.sports_rental.UserAuthentication.UserLoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserLoginTest
{

    @Test
    public void loginTest()
    {
        UserLoginDaoMock userLoginDaoMock = new UserLoginDaoMock();
        IUserLoginService userLoginService = UserLoginService.instance();
        String email ="group1@gmail.com";
        String password = "123456789";
        Assertions.assertEquals(4,userLoginService.login(userLoginDaoMock,email,password));
    }

    @Test
    public void loginErrorTest()
    {
        UserLoginDaoMock userLoginDaoMock = new UserLoginDaoMock();
        IUserLoginService userLoginService = UserLoginService.instance();
        String email ="group1@gmail.com";
        String password = "123";
        Assertions.assertEquals(0,userLoginService.login(userLoginDaoMock,email,password));
    }
}
