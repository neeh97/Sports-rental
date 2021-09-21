package com.group1.sports_rental.UserAuthentication;

public class UserLogin
{
    static UserLogin instance = null;

    public static UserLogin instance()
    {
        if(instance == null)
        {
            instance = new UserLogin();
        }
        return instance;
    }

    private String email;
    private String password;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
