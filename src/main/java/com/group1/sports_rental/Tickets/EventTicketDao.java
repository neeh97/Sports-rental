package com.group1.sports_rental.Tickets;

import com.group1.sports_rental.Utils.DbConnectionSingleton;
import java.sql.*;
import java.util.*;

public class EventTicketDao implements IEventTicketDao
{
    DbConnectionSingleton dbConnectionInstance= DbConnectionSingleton.getInstance();

    public Float fetchTicketPrice(String eventId)
    {
        Connection connection = dbConnectionInstance.getConnection();
        Float perTicketPrice = 0.0f;
        try
        {
            String getRentalIdQuery = "Select ticket_price from event where event_id='"+eventId+"';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getRentalIdQuery);
            while (resultSet.next())
            {
                perTicketPrice = resultSet.getFloat("ticket_price");
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
        return perTicketPrice;
    }

    public String insertTicketData(String eventId,Integer tickets, Float totalTicketPrice,Long userId)
    {
        UUID uuid= UUID.randomUUID();
        String ticketId = uuid.toString().replace("-","");
        final String paymentStatus;
        paymentStatus= "pending";
        Connection connection = dbConnectionInstance.getConnection();
        try
        {
            String insertQuery = " Insert into ticket" +
                    "(ticket_id,tickets_booked,amount,user_id,event_id,payment_status)"+
                    "values(?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1,ticketId);
            preparedStatement.setInt(2,tickets);
            preparedStatement.setFloat(3, totalTicketPrice);
            preparedStatement.setLong(4,userId);
            preparedStatement.setString(5,eventId);
            preparedStatement.setString(6, paymentStatus);
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
        return ticketId;
    }

    public Integer fetchTicketCount(String eventId)
    {
        Integer ticketCount = 0;
        Connection connection = dbConnectionInstance.getConnection();
        try
        {
            String getRentalIdQuery = "Select total_tickets from event where event_id='"+eventId+"';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getRentalIdQuery);
            while (resultSet.next())
            {
                ticketCount = resultSet.getInt("total_tickets");
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
        return ticketCount;
    }

    public void updateTicketCount(String eventId,Integer ticketsLeft)
    {
        Connection connection = dbConnectionInstance.getConnection();
        try
        {
            String updateQuery = "Update event set tickets_left=" + ticketsLeft + " where event_id='" + eventId + "';";
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

    public List<Map<String, Object>> fetchEventTicketsOfUser(Long userId)
    {
        Connection connection = dbConnectionInstance.getConnection();
        List<Map<String, Object>> resultList = new ArrayList<>();
        try
        {
            String selectQuery = "SELECT * FROM ticket WHERE user_id=" + userId + " AND payment_status='complete'";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("ticketId", resultSet.getString("ticket_id"));
                resultMap.put("eventId", resultSet.getString("event_id"));
                resultMap.put("ticketsBooked", resultSet.getInt("tickets_booked"));
                resultMap.put("paymentId", resultSet.getString("payment_id"));
                resultList.add(resultMap);
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
        return resultList;
    }

}
