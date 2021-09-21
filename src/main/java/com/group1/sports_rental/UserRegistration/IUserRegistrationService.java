package com.group1.sports_rental.UserRegistration;

public interface IUserRegistrationService
{
    void register(IUserRegistrationDao userRegistrationDao,UserRegistration userRegistration);
    Boolean findByEmail(String email, IUserRegistrationDao userRegistrationDao);
    UserRegistration findOne(Long userId, IUserRegistrationDao userRegistrationDao);
}
