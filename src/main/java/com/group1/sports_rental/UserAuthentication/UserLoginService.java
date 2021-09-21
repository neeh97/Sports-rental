package com.group1.sports_rental.UserAuthentication;

public class UserLoginService implements IUserLoginService
{
    static UserLoginService instance = null;

    public static UserLoginService instance()
    {
        if(instance == null)
        {
            instance = new UserLoginService();
        }
        return instance;
    }

    public Long login(IUserLoginDao userLoginDao, String email, String password)
    {
        return userLoginDao.login(email,password);
    }
}
