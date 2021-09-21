package com.group1.sports_rental.UserAuthentication;

public interface IUserLoginService
{
    Long login(IUserLoginDao userLoginDao, String email, String password);
}
