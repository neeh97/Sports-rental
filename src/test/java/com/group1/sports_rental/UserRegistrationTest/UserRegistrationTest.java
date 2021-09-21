package com.group1.sports_rental.UserRegistrationTest;

import com.group1.sports_rental.UserRegistration.IUserRegistrationService;
import com.group1.sports_rental.UserRegistration.UserRegistration;
import com.group1.sports_rental.UserRegistration.UserRegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRegistrationTest
{

    @Test
    public void isValidPasswordTest()
    {
        UserRegistration userRegistration =UserRegistration.instance();
        userRegistration.setPassword("Password@123");
        Assertions.assertEquals(true,userRegistration.isValidPassword());
    }

    @Test
    public void isValidPasswordErrorTest()
    {
        UserRegistration userRegistration =UserRegistration.instance();
        userRegistration.setPassword("Pass");
        Assertions.assertEquals(false,userRegistration.isValidPassword());
    }

    @Test
    public void isValidConfirmPasswordTest()
    {
        UserRegistration userRegistration =UserRegistration.instance();
        userRegistration.setPassword("Password@123");
        userRegistration.setConfirmPassword("Password@123");
        Assertions.assertEquals(true,userRegistration.isValidConfirmPassword());
    }

    @Test
    public void isValidConfirmPasswordErrorTest()
    {
        UserRegistration userRegistration =UserRegistration.instance();
        userRegistration.setPassword("Password@123");
        userRegistration.setConfirmPassword("Password");
        Assertions.assertEquals(false,userRegistration.isValidConfirmPassword());
    }

    @Test
    public void isValidEmailTest()
    {
        UserRegistration userRegistration =UserRegistration.instance();
        userRegistration.setEmail("group123@gmail.com");
        Assertions.assertEquals(true,userRegistration.isValidEmail());
    }

    @Test
    public void isValidEmailErrorTest()
    {
        UserRegistration userRegistration =UserRegistration.instance();
        userRegistration.setEmail("group123");
        Assertions.assertEquals(false,userRegistration.isValidEmail());
    }

    @Test
    public void isValidFirstNameTest()
    {
        UserRegistration userRegistration =UserRegistration.instance();
        userRegistration.setFirstName("Group");
        Assertions.assertEquals(true,userRegistration.isValidFirstName());
    }

    @Test
    public void isValidFirstNameErrorTest()
    {
        UserRegistration userRegistration =UserRegistration.instance();
        userRegistration.setFirstName("grp123");
        Assertions.assertEquals(false,userRegistration.isValidFirstName());
    }

    @Test
    public void isValidLastNameTest()
    {
        UserRegistration userRegistration =UserRegistration.instance();
        userRegistration.setLastName("tech");
        Assertions.assertEquals(true,userRegistration.isValidLastName());
    }

    @Test
    public void isValidLastNameErrorTest()
    {
        UserRegistration userRegistration =UserRegistration.instance();
        userRegistration.setLastName("tech123");
        Assertions.assertEquals(false,userRegistration.isValidLastName());
    }

    //Referred https://git.cs.dal.ca/rhawkey/csci5308/-/blob/master/TDD/TutorialSampleCode/intellijtdd/src/test/java/UserTest.java
    @Test
    public void registerTest()
    {
        UserRegistration userRegistration =UserRegistration.instance();
        IUserRegistrationService userRegistrationService = UserRegistrationService.instance();
        try {
            UserRegistrationDaoMock userRegistrationDaoMock = new UserRegistrationDaoMock();
            userRegistrationService.register(userRegistrationDaoMock,userRegistration);
        }
        catch (Exception e){
            Assertions.fail("User Registration failed due to "+e.getMessage() );
        }
    }

    @Test
    public void findByEmailTest()
    {
        IUserRegistrationService userRegistrationService = UserRegistrationService.instance();
        UserRegistrationDaoMock userRegistrationDaoMock = new UserRegistrationDaoMock();
        String email ="group1@gmail.com";
        Assertions.assertEquals(false,userRegistrationService.findByEmail(email,userRegistrationDaoMock));
    }

    @Test
    public void findByEmailErrorTest()
    {
        IUserRegistrationService userRegistrationService = UserRegistrationService.instance();
        UserRegistrationDaoMock userRegistrationDaoMock = new UserRegistrationDaoMock();
        String email ="group15@gmail.com";
        Assertions.assertEquals(true,userRegistrationService.findByEmail(email,userRegistrationDaoMock));
    }

    @Test
    public void findOneTest()
    {
        IUserRegistrationService userRegistrationService = UserRegistrationService.instance();
        UserRegistrationDaoMock userRegistrationDaoMock = new UserRegistrationDaoMock();
        long userId = 4;
        Assertions.assertEquals("Group",userRegistrationService.findOne(userId,userRegistrationDaoMock).getFirstName());
    }
}
