package com.group1.sports_rental.Payment;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import com.group1.sports_rental.Utils.DbConnectionSingleton;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.UUID;

@Repository
public class PaymentDao implements IPaymentDao
{
    DbConnectionSingleton dbConnectionInstance= DbConnectionSingleton.getInstance();

    public Integer fetchRentalId(ProductAdvertisement productAdvertisement) throws ParseException
    {
        Connection connection = null;
        Integer rentalId = 0;
        try
        {
            connection = dbConnectionInstance.getConnection();
            String getRentalIdQuery = "Select rental_id from rent where start_date='"+productAdvertisement.getStartDate()+"' and " +
                    "end_date='"+productAdvertisement.getEndDate()+"' and rented_item_id='"+productAdvertisement.getId()+"';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getRentalIdQuery);
            while (resultSet.next())
            {
                rentalId = resultSet.getInt("rental_id");
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            dbConnectionInstance.closeConnection(connection);
        }
        return rentalId;
    }

    public Payment displayRentalValues(Integer rentalId, ProductAdvertisement productAdvertisement)
    {
        Connection connection = null;
        Payment payment = new Payment();
        try
        {
            connection = dbConnectionInstance.getConnection();
            String selectQuery = "Select * from rent r inner join product p on r.rented_item_id=p.product_id where r.rental_id="+ rentalId+";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while(resultSet.next())
            {
                payment.setRentalId(resultSet.getInt("rental_id"));
                payment.setAmount(resultSet.getFloat("total_amount"));
                productAdvertisement.setProductName(resultSet.getString("product_name"));
                productAdvertisement.setProductDescription(resultSet.getString("product_description"));
                productAdvertisement.setStartDate(resultSet.getDate("start_date"));
                productAdvertisement.setEndDate(resultSet.getDate("end_date"));
                payment.setRenterId(resultSet.getInt("user_id"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            dbConnectionInstance.closeConnection(connection);
        }
    return payment;
    }

    public void insertPaymentData(HashMap<String,Object> transactionDetails, Payment payment)
    {
        Connection connection = null;
        UUID uuid= UUID.randomUUID();
        String uniquePaymentId = uuid.toString().replace("-","");
        payment.setPaymentId(uniquePaymentId);
        try
        {
            connection = dbConnectionInstance.getConnection();
            String insertQuery = "Insert into payment(amount,email,currency,payment_status,rental_id,renter_id,payment_mode,paymentId,transaction_id,user_id)" +
                    "values(?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setFloat(1, (Integer) transactionDetails.get("amount"));
            preparedStatement.setString(2, (String) transactionDetails.get("email"));
            preparedStatement.setString(3, (String) transactionDetails.get("currency"));
            preparedStatement.setString(4, (String) transactionDetails.get("paymentStatus"));
            preparedStatement.setInt(5,payment.getRentalId());
            preparedStatement.setInt(6,payment.getRenterId());
            preparedStatement.setString(7,payment.getPaymentMode());
            preparedStatement.setString(8,payment.getPaymentId());
            preparedStatement.setString(9, (String) transactionDetails.get("transactionId"));
            preparedStatement.setLong(10,payment.getUserId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            dbConnectionInstance.closeConnection(connection);
        }
    }
    public void updateRentalStatus(Integer rentalId)
    {
        Connection connection = null;
        try
        {
            connection = dbConnectionInstance.getConnection();
            String updateQuery = "Update rent set status="+true+" where rental_id="+rentalId+";";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            dbConnectionInstance.closeConnection(connection);
        }
    }

    public void updatePaymentCompleteStatus(Payment payment)
    {
        String ticketId = payment.getTicketId();
        Integer rentalId = payment.getRentalId();
        String paymentId = payment.getPaymentId();
        Connection connection = null;
        try
        {
            connection = dbConnectionInstance.getConnection();
            if (rentalId == 0 && ticketId.isEmpty())
            {
                String updateQuery = "Update payment set payment_status='"+Payment.PaymentStatus.topUp+"' where paymentId='"+paymentId+"' and payment_status='"+Payment.PaymentStatus.payComplete+"';";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.executeUpdate();
            }
            else if (ticketId.length() > 0)
            {
                updateTicketPaymentStatus(ticketId, paymentId);
            }
            else
            {
                updateRentalStatus(rentalId);
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            dbConnectionInstance.closeConnection(connection);
        }
    }

    public String fetchTransaction(String paymentId)
    {
        String transactionId = "";
        Connection connection = null;
        try
        {
            connection = dbConnectionInstance.getConnection();
            String updateQuery = "select transaction_id from payment where paymentId='"+paymentId+"';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(updateQuery);
            while (resultSet.next())
            {
                transactionId = resultSet.getString("transaction_id");
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            dbConnectionInstance.closeConnection(connection);
        }
        return transactionId;
    }

    public void updateRefundStatus(HashMap<String, Object> refundMap,String paymentId)
    {
        Connection connection = null;
        try
        {
            connection = dbConnectionInstance.getConnection();
            String paymentStatus = (String) refundMap.get("paymentStatus");
            String refundId = (String) refundMap.get("refundTransactionId");
            String updateStatusQuery = "Update payment set payment_status ='" + paymentStatus + "' , " +
                    "refund_id='" + refundId + "' where paymentId='" + paymentId + "';";
            PreparedStatement preparedStatement = connection.prepareStatement(updateStatusQuery);
            preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        finally
        {
            dbConnectionInstance.closeConnection(connection);
        }
    }

    public void updateTicketPaymentStatus(String ticketId,String paymentId) throws SQLException
    {
        Connection connection = null;
        try
        {
            connection = dbConnectionInstance.getConnection();
            String updateQuery = "Update ticket set payment_status='"+Payment.PaymentStatus.complete+"', payment_id='" + paymentId + "' where ticket_id='" + ticketId + "';";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            dbConnectionInstance.closeConnection(connection);
        }
    }

    public void updateTicketRefundStatus(String ticketId, String paymentId) throws SQLException
    {
        Connection connection = null;
        try
        {
            connection = dbConnectionInstance.getConnection();
            String updateQuery = "Update ticket set payment_status='"+Payment.PaymentStatus.refund+"' where ticket_id='"+ticketId+"';";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            dbConnectionInstance.closeConnection(connection);
        }
    }

    public Integer fetchGroundRentalId(GroundAdvertisement groundAdvertisement) throws ParseException
    {
        Connection connection = null;
        Integer rentalId = 0;
        try
        {
            connection = dbConnectionInstance.getConnection();
            String getRentalIdQuery = "Select rental_id from rent where start_date='"+groundAdvertisement.getStartDate()+"' and " +
                    "end_date='"+groundAdvertisement.getEndDate()+"' and rented_item_id='"+groundAdvertisement.getId()+"';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getRentalIdQuery);
            while (resultSet.next())
            {
                rentalId = resultSet.getInt("rental_id");
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            dbConnectionInstance.closeConnection(connection);
        }
        return rentalId;
    }

    public Payment displayGroundRentalData(Integer rentalId, GroundAdvertisement groundAdvertisement)
    {
        Connection connection = null;
        Payment payment = new Payment();
        try
        {
            connection = dbConnectionInstance.getConnection();
            String selectQuery = "Select * from rent r inner join ground p on r.rented_item_id=p.ground_id where r.rental_id="+ rentalId+";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while(resultSet.next())
            {
                payment.setRentalId(resultSet.getInt("rental_id"));
                payment.setAmount(resultSet.getFloat("total_amount"));
                groundAdvertisement.setGroundName(resultSet.getString("ground_name"));
                groundAdvertisement.setAddress(resultSet.getString("address"));
                groundAdvertisement.setStartDate(resultSet.getDate("available_from_date"));
                groundAdvertisement.setEndDate(resultSet.getDate("available_to_date"));
                payment.setRenterId(resultSet.getInt("user_id"));
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            dbConnectionInstance.closeConnection(connection);
        }
        return payment;
    }
}
