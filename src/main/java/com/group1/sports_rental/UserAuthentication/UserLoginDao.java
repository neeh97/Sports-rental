package com.group1.sports_rental.UserAuthentication;

import com.group1.sports_rental.Utils.DbConnectionSingleton;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserLoginDao implements IUserLoginDao
{
        DbConnectionSingleton dbConnection= DbConnectionSingleton.getInstance();

        public Long login(String email,String password)
        {
            Long userId =0L;
            Connection connection=dbConnection.getConnection();
            try
            {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE email ='"+email+"'and password='"+password+"'");
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next())
                {
                    if (((email).equals(resultSet.getString("email"))) && ((password).equals(resultSet.getString("password"))))
                    {
                        userId =resultSet.getLong("userId");
                    }
                    else
                    {
                        userId =0l;
                    }
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
            return userId;
        }
    }



