package com.group1.sports_rental.UserRegistration;

public class UserRegistration
{
    static UserRegistration instance = null;

    public static UserRegistration instance()
    {
        if(instance == null)
        {
            instance = new UserRegistration();
        }
        return instance;
    }

    private final Integer passwordLength = 8;
    private final String emailFormat = "^(.+)@(.+).(.+)$";
    private final String nameFormat="^[a-zA-Z]*$";

    private Long userId;
    private String email;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String Address;
    private Long phoneNumber;
    private String city;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

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

    public String getConfirmPassword()
    {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getAddress()
    {
        return Address;
    }

    public void setAddress(String address)
    {
        Address = address;
    }

    public Long getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public Boolean isValidPassword()
    {
        if((this.password.length()>=passwordLength) && (this.password!=null))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean isValidConfirmPassword()
    {
        if(((this.confirmPassword!=null) && (this.password.equals(this.confirmPassword))))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean isValidEmail()
    {
        if(this.email!=null && this.email.matches(emailFormat))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean isValidFirstName()
    {
        if(this.firstName!=null && this.firstName.matches(nameFormat))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean isValidLastName()
    {
        if(this.lastName!=null && this.lastName.matches(nameFormat))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}