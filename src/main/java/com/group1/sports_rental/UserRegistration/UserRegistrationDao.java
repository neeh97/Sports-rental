package com.group1.sports_rental.UserRegistration;

import com.group1.sports_rental.Utils.DbConnectionSingleton;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRegistrationDao implements IUserRegistrationDao
{
    DbConnectionSingleton dbConnection= DbConnectionSingleton.getInstance();

    public void register(UserRegistration userRegistration)
    {
        Connection connection = dbConnection.getConnection();
        try
        {
            String sql = "insert into user(email,password,confirm_password,first_name,last_name,phone_number,address,city) values('" + userRegistration.getEmail() + "','" + userRegistration.getPassword() + "','" + userRegistration.getConfirmPassword() + "','" + userRegistration.getFirstName() + "','" + userRegistration.getLastName() + "','" + userRegistration.getPhoneNumber() + "','" + userRegistration.getAddress() + "','" + userRegistration.getCity() + "');";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            dbConnection.closeConnection(connection);
        }
    }

    public Boolean findByEmail(String email)
    {
        Boolean isExistedUser=false;
        Connection connection= dbConnection.getConnection();
        try
        {
            String user = "Select * from user where email = '"+email+"'";
            PreparedStatement statement=connection.prepareStatement(user);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next())
            {
                if (resultSet.getString("email").equals(email))
                {
                    isExistedUser = false;
                }
            }
            else
                {
                    isExistedUser = true;
                }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            dbConnection.closeConnection(connection);
        }
        return isExistedUser;
    }

    public UserRegistration findOne(Long userId)
    {
        UserRegistration userRegistration = null;
        Connection connection = dbConnection.getConnection();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE userId = "+userId+"");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                userRegistration = UserRegistration.instance();
                userRegistration.setUserId(resultSet.getLong("userId"));
                userRegistration.setLastName(resultSet.getString("last_name"));
                userRegistration.setFirstName(resultSet.getString("first_name"));
                userRegistration.setCity(resultSet.getString("city"));
                userRegistration.setAddress(resultSet.getString("address"));
                userRegistration.setEmail(resultSet.getString("email"));
                userRegistration.setPhoneNumber(resultSet.getLong("phone_number"));
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            dbConnection.closeConnection(connection);
        }
        return userRegistration;
    }
}






