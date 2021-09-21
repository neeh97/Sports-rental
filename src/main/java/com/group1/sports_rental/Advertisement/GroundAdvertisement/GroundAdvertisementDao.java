package com.group1.sports_rental.Advertisement.GroundAdvertisement;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import com.group1.sports_rental.Utils.DbConnectionSingleton;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Populated location table from https://simplemaps.com/data/canada-cities
@Repository
public class GroundAdvertisementDao implements IGroundAdvertisementDao
{
    DbConnectionSingleton databaseInstance= DbConnectionSingleton.getInstance();

    static GroundAdvertisementDao instance = null;

    public static GroundAdvertisementDao instance()
    {
        if (instance == null)
        {
            instance = new GroundAdvertisementDao();
        }
        return instance;
    }

    public String fetchCity(String state,String city)
    {
        String validCity = "";
        String stateLower = state.toLowerCase();
        String cityLower = city.toLowerCase();
        String citySpaceRemoved = cityLower.replace(" ","");
        Connection connection = null;
        try
        {
            connection = databaseInstance.getConnection();
            String selectQuery = "Select city from location where state='"+stateLower+"' and city='"+citySpaceRemoved+"';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next())
            {
                validCity = resultSet.getString("city");
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            databaseInstance.closeConnection(connection);
        }
        return validCity;
    }

    public void insertGround(GroundAdvertisement groundAdvertisement)
    {
        UUID uuid= UUID.randomUUID();
        String uniqueGroundId = "gr_"+uuid.toString().replace("-","");
        Connection connection = null;
        try
        {
            String insertQuery = " Insert into ground" +
                    "(ground_id, address, size, seating_capacity, rental_cost, available_from_date, available_to_date, city, user_id, state, ground_name,image,sports_type)" +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
            connection = databaseInstance.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1,uniqueGroundId);
            preparedStatement.setString(2,groundAdvertisement.getAddress());
            preparedStatement.setString(3,groundAdvertisement.getSize());
            preparedStatement.setString(4,groundAdvertisement.getSeatingCapacity());
            preparedStatement.setFloat(5,groundAdvertisement.getRentalCost());
            preparedStatement.setDate(6,groundAdvertisement.getStartDate());
            preparedStatement.setDate(7,groundAdvertisement.getEndDate());
            preparedStatement.setString(8,groundAdvertisement.getCity());
            preparedStatement.setLong(9, groundAdvertisement.getUserId());
            preparedStatement.setString(10, groundAdvertisement.getState());
            preparedStatement.setString(11, groundAdvertisement.getGroundName());
            preparedStatement.setBytes(12, groundAdvertisement.getItemImage());
            preparedStatement.setString(13, groundAdvertisement.getSportsType());
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            databaseInstance.closeConnection(connection);
        }
    }

    public List<GroundAdvertisement> applyFilters(String city, String sportsType, String location)
    {
        Connection connection = databaseInstance.getConnection();
        List<GroundAdvertisement> groundAdvertisementList = new ArrayList<>();
        try
        {
            String searchQuery = "SELECT * FROM ground WHERE city = '" + city + "'";
            if (StringUtils.hasText(sportsType)) {
                searchQuery += "AND sports_type='" + sportsType + "'";
            }
            if (StringUtils.hasText(location)) {
                searchQuery += "AND address = '" + location + "'";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            groundAdvertisementList = convertResultSet(resultSet);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            databaseInstance.closeConnection(connection);
        }
        return groundAdvertisementList;
    }

    public List<GroundAdvertisement> getDefaultGrounds(String city)
    {
        Connection connection = databaseInstance.getConnection();
        List<GroundAdvertisement> groundAdvertisementList = new ArrayList<>();
        try
        {
            String searchQuery = "SELECT * FROM ground WHERE city = '" + city + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            groundAdvertisementList = convertResultSet(resultSet);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            databaseInstance.closeConnection(connection);
        }
        return groundAdvertisementList;
    }

    public List<GroundAdvertisement> searchGroundName(String city, String searchText)
    {
        List<GroundAdvertisement> groundAdvertisementList = new ArrayList<>();
        Connection connection = databaseInstance.getConnection();
        try
        {
            String searchQuery = "SELECT * FROM ground WHERE city = '" + city + "' AND ground_name LIKE '" + searchText + "%'";
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            groundAdvertisementList = convertResultSet(resultSet);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            databaseInstance.closeConnection(connection);
        }
        return groundAdvertisementList;
    }

    private List<GroundAdvertisement> convertResultSet(ResultSet resultSet) throws SQLException
    {
        List<GroundAdvertisement> groundAdvertisementList = new ArrayList<>();
        AbstractFactory factory = new ConcreteFactory();
        while (resultSet.next())
        {
            GroundAdvertisement groundAdvertisement = factory.createGround();
            groundAdvertisement.setId(resultSet.getString("ground_id"));
            groundAdvertisement.setGroundName(resultSet.getString("ground_name"));
            groundAdvertisement.setSportsType(resultSet.getString("sports_type"));
            groundAdvertisement.setAddress(resultSet.getString("address"));
            groundAdvertisement.setSize(resultSet.getString("size"));
            groundAdvertisementList.add(groundAdvertisement);
        }
        return groundAdvertisementList;
    }

    public GroundAdvertisement findOne(String groundId)
    {
        GroundAdvertisement groundAdvertisement = null;
        Connection connection = null;
        AbstractFactory factory = new ConcreteFactory();
        try
        {
            connection = databaseInstance.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ground WHERE ground_id = '"+groundId+"'");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                groundAdvertisement = factory.createGround();
                groundAdvertisement.setGroundName(resultSet.getString("ground_name"));
                groundAdvertisement.setId(groundId);
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            databaseInstance.closeConnection(connection);
        }
        return groundAdvertisement;
    }
}
