package com.group1.sports_rental.GroundPage;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Utils.DbConnectionSingleton;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class GroundPageDao implements IGroundPageDao
{
    DbConnectionSingleton dbConnection= DbConnectionSingleton.getInstance();

    public GroundAdvertisement displayGround(String groundId)
    {
        AbstractFactory factory = new ConcreteFactory();
        GroundAdvertisement groundAdvertisement = factory.createGround();
        Connection connection = dbConnection.getConnection();
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ground WHERE ground_id='" + groundId + "'");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                groundAdvertisement.setGroundName(resultSet.getString("ground_name"));
                groundAdvertisement.setAddress(resultSet.getString("address"));
                groundAdvertisement.setSize(resultSet.getString("size"));
                groundAdvertisement.setSeatingCapacity(resultSet.getString("seating_capacity"));
                groundAdvertisement.setRentalCost(resultSet.getFloat("rental_cost"));
                groundAdvertisement.setCity(resultSet.getString("city"));
                groundAdvertisement.setStartDate(resultSet.getDate("available_from_date"));
                groundAdvertisement.setEndDate(resultSet.getDate("available_to_date"));
                groundAdvertisement.setSportsType(resultSet.getString("sports_type"));
                groundAdvertisement.setItemImage(resultSet.getBytes("image"));
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
        return groundAdvertisement;
    }

    public InputStream getGroundImage(String groundId)
    {
        Connection connection = dbConnection.getConnection();
        InputStream inputStream = null;
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ground WHERE ground_id='" + groundId + "'");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                inputStream = resultSet.getBinaryStream("image");
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
        return inputStream;
    }
}

