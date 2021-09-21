package com.group1.sports_rental.CustomerPage;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisementDao;
import com.group1.sports_rental.Advertisement.GroundAdvertisement.IGroundAdvertisementDao;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.IProductAdvertisementDao;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisementDao;
import com.group1.sports_rental.Utils.DbConnectionSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerPageDao implements ICustomerPageDao
{

    private final static String productIdPrefix = "pr_";
    private final static String groundIdPrefix = "gr_";

    @Autowired
    IProductAdvertisementDao productAdvertisementDao = ProductAdvertisementDao.instance();
    IGroundAdvertisementDao groundAdvertisementDao = GroundAdvertisementDao.instance();
    DbConnectionSingleton database = DbConnectionSingleton.getInstance();

    public List<RentalHistory> getUserRentalHistory(Long userId)
    {
        List<RentalHistory> rentalHistoryList = new ArrayList<>();
        Connection connection = null;
        try
        {
            connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM rent WHERE user_id = "+userId+"");
            ResultSet resultSet = preparedStatement.executeQuery();
            rentalHistoryList = convertResultSetToRentalHistory(resultSet);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            database.closeConnection(connection);
        }
        return rentalHistoryList;
    }

    private List<RentalHistory> convertResultSetToRentalHistory(ResultSet resultSet)
    {
        List<RentalHistory> rentalHistoryList = new ArrayList<>();
        try
        {
            while (resultSet.next())
            {
                RentalHistory rentalHistory = new RentalHistory();
                rentalHistory.setAmount(resultSet.getFloat("total_amount"));
                rentalHistory.setEndDate(resultSet.getDate("end_date"));
                rentalHistory.setStartDate(resultSet.getDate("start_date"));
                String rentId = resultSet.getString("rented_item_id");
                if (rentId.startsWith(productIdPrefix))
                {
                    ProductAdvertisement productAdvertisement = productAdvertisementDao.findOne(resultSet.getString("rented_item_id"));
                    rentalHistory.setProductName(productAdvertisement.getProductName());
                }
                if (rentId.startsWith(groundIdPrefix))
                {
                    GroundAdvertisement groundAdvertisement = groundAdvertisementDao.findOne(rentId);
                    rentalHistory.setProductName(groundAdvertisement.getGroundName());
                }
                rentalHistoryList.add(rentalHistory);
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return rentalHistoryList;
    }

    public CustomerCredit getCreditsOfUser(Long userId)
    {
        CustomerCredit customerCredit = null;
        Connection connection = null;
        try
        {
            connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM credit WHERE user_id = " + userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                customerCredit = convertResultSetToCustomerCredit(resultSet);
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            database.closeConnection(connection);
        }
        return customerCredit;
    }

    public void topUpCredits(Long userId, String creditAmount)
    {
        Connection connection = database.getConnection();
        try
        {
            String getCredits = "SELECT * FROM credit WHERE user_id = "+userId;
            PreparedStatement getStatement = connection.prepareStatement(getCredits);
            ResultSet resultSet = getStatement.executeQuery();
            if (resultSet.next())
            {
                Float currentCredits = resultSet.getFloat("credits");
                Float updatedCredits = currentCredits+Float.parseFloat(creditAmount);
                String updateCredits = "UPDATE credit SET credits = "+updatedCredits+" WHERE user_id="+userId;
                getStatement = connection.prepareStatement(updateCredits);
                getStatement.executeUpdate();
            }
            else
            {
                String insertQuery = "INSERT INTO credit (credits, user_id) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, creditAmount);
                preparedStatement.setString(2, userId.toString());
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            database.closeConnection(connection);
        }
    }

    private CustomerCredit convertResultSetToCustomerCredit(ResultSet resultSet)
    {
        CustomerCredit customerCredit = new CustomerCredit();
        try
        {
            customerCredit.setCredits(resultSet.getFloat("credits"));
            customerCredit.setUserId(resultSet.getString("user_id"));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return customerCredit;
    }

    public List<Map<String, Object>> refundCredits(Long userId)
    {
        List<Map<String, Object>> paymentList = new ArrayList<>();
        Connection connection = null;
        try
        {
            String getPayments = "SELECT * FROM payment WHERE user_id = " + userId + " AND payment_status = 'top-up complete'";
            connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getPayments);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Map<String, Object> payment = new HashMap<>();
                payment.put("paymentId", resultSet.getString("paymentId"));
                payment.put("amount", resultSet.getFloat("amount"));
                paymentList.add(payment);
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            database.closeConnection(connection);
        }
        return paymentList;
    }

    public void removeCredits(Long userId, Float amount)
    {
        Connection connection = null;
        try
        {
            connection = database.getConnection();
            String getCreditsQuery = "SELECT * FROM credit WHERE user_id = "+userId;
            PreparedStatement preparedStatement = connection.prepareStatement(getCreditsQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                Float currentCredits = resultSet.getFloat("credits");
                Float balanceCredits = currentCredits - amount;
                String removeCreditsQuery = "UPDATE credit SET credits = "+balanceCredits+" WHERE user_id = " + userId;
                preparedStatement = connection.prepareStatement(removeCreditsQuery);
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            database.closeConnection(connection);
        }
    }

    public Boolean callRefundApi(String apiUrl)
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, Boolean.class);
    }
}
