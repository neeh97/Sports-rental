package com.group1.sports_rental.UserRegistration;

public class UserRegistrationService implements IUserRegistrationService
{
    static UserRegistrationService instance = null;

    public static UserRegistrationService instance()
    {
        if(instance == null)
        {
            instance = new UserRegistrationService();
        }
        return instance;
    }

    public void register(IUserRegistrationDao userRegistrationDao,UserRegistration userRegistration)
    {
        userRegistrationDao.register(userRegistration);
    }

    public Boolean findByEmail(String email, IUserRegistrationDao userRegistrationDao)
    {
        Boolean isExistedUser = userRegistrationDao.findByEmail(email);
        return isExistedUser;
    }

    public UserRegistration findOne(Long userId, IUserRegistrationDao userRegistrationDao)
    {
        return userRegistrationDao.findOne(userId);
    }
}
