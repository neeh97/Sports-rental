package com.group1.sports_rental.UserRegistration;

public interface IUserRegistrationDao
{
    void register(UserRegistration userRegistration) ;
    Boolean findByEmail(String email);
    UserRegistration findOne(Long userId);
}
