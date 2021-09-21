package com.group1.sports_rental.Rental;

import com.group1.sports_rental.Utils.DbConnectionSingleton;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public abstract class RentDao
{
    private final Integer minimumCredits = 200;
    DbConnectionSingleton dbConnection= DbConnectionSingleton.getInstance();

    public Boolean checkAvailability(Date startDate, Date endDate, String rentedItemId)
    {
        Boolean isAvailable = true;
        Connection connection = dbConnection.getConnection();
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM rent WHERE rented_item_id='" + rentedItemId + "'");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Date existedStartDate = resultSet.getDate("start_date");
                Date existedEndDate = resultSet.getDate("end_date");
                Boolean status = resultSet.getBoolean("status");
                if(status)
                {
                    if((startDate.after(existedStartDate) && startDate.before(existedEndDate)) || (endDate.after(existedStartDate) && endDate.before(existedEndDate)))
                    {
                        isAvailable = false;
                        break;
                    }
                }
                else
                {
                    isAvailable = true;
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
        return isAvailable;
    }

    public Boolean checkCredits(Long userId)
    {
        Boolean isSufficientCredits = false;
        Connection connection = dbConnection.getConnection();
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM credit WHERE user_id='" + userId + "'");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                Float credits = resultSet.getFloat("credits");
                if(credits>=minimumCredits)
                {
                    isSufficientCredits = true;
                }
                else
                {
                    isSufficientCredits = false;
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
        return isSufficientCredits;
    }

    public abstract Double calculateRent(String rentedItemId,Date startDate,Date endDate);

    public void insertRentalDetails(Date startDate, Date endDate,String rentedItemId,Long userId)
    {
        Connection connection = dbConnection.getConnection();
        try
        {
            Rental rental = Rental.instance();
            Double totalCost = calculateRent(rentedItemId, startDate, endDate);
            rental.setTotalCost(totalCost);
            rental.setRentalStatus(false);
            String sql = "insert into rent(rental_id,start_date,end_date,total_amount,status,rented_item_id,user_id) values(LPAD(FLOOR(RAND() * 999999.99), 6, '0'),'" + startDate + "','" + endDate + "','" + rental.getTotalCost() + "','" + rental.getRentalStatus() + "','" + rentedItemId + "','" + userId + "' )";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            dbConnection.closeConnection(connection);
        }
    }
}
