package com.group1.sports_rental.Rental;

import com.group1.sports_rental.Utils.DbConnectionSingleton;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class RentProductDao extends RentDao
{
    DbConnectionSingleton dbConnection= DbConnectionSingleton.getInstance();
    Rental rental = Rental.instance();

    public Double calculateRent(String rentedItemId,Date startDate,Date endDate)
    {
        Float costPerDay;
        Double rent = 0d;
        Connection connection = dbConnection.getConnection();
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE product_id='" + rentedItemId + "'");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Date productAvailableFrom = resultSet.getDate("available_from_date");
                Date productAvailableTo = resultSet.getDate("available_to_date");
                costPerDay = resultSet.getFloat("rental_cost");
                Long days = rental.calculateDays(startDate, endDate);
                if((startDate.after(productAvailableFrom) && startDate.before(productAvailableTo)) || (endDate.after(productAvailableFrom) && endDate.before(productAvailableTo)))
                {
                    if (days > 0)
                    {
                        rent = Double.valueOf(days * costPerDay);
                    }
                    else
                    {
                        rent = 0d;
                    }
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
        return rent;
    }
}
