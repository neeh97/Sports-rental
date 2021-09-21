package com.group1.sports_rental.EventPage;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import com.group1.sports_rental.Utils.DbConnectionSingleton;
import java.io.InputStream;
import java.sql.*;

public class EventPageDao implements IEventPageDao
{
    DbConnectionSingleton dbConnectionInstance= DbConnectionSingleton.getInstance();

    public EventAdvertisement displayEvent(String eventId)
    {
        AbstractFactory factory = new ConcreteFactory();
        EventAdvertisement eventAdvertisement  = factory.createEvent();
        Connection connection = dbConnectionInstance.getConnection();
        try
        {
            String fetchQuery = " Select * from event where event_id='"+eventId+"';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(fetchQuery);
            while (resultSet.next())
            {
                eventAdvertisement.setEventId(resultSet.getString("event_id"));
                eventAdvertisement.setEventName(resultSet.getString("event_name"));
                eventAdvertisement.setEventAddress(resultSet.getString("event_address"));
                eventAdvertisement.setEventCity(resultSet.getString("event_city"));
                eventAdvertisement.setEventState(resultSet.getString("event_state"));
                eventAdvertisement.setTicketPrice(resultSet.getFloat("ticket_price"));
                eventAdvertisement.setTotalTickets(resultSet.getInt("total_tickets"));
                eventAdvertisement.setTicketsBooked(resultSet.getInt("tickets_booked"));
                eventAdvertisement.setTicketsLeft(resultSet.getInt("tickets_left"));
                eventAdvertisement.setEventDate(resultSet.getDate("event_date"));
                eventAdvertisement.setStartTime(resultSet.getTime("start_time"));
                eventAdvertisement.setEndTime(resultSet.getTime("end_time"));
                eventAdvertisement.setItemImage(resultSet.getBytes("image"));
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
    return eventAdvertisement;
    }

    public InputStream getEventImage(String eventId)
    {
        Connection connection = dbConnectionInstance.getConnection();
        InputStream inputStream = null;
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM event WHERE event_id='" + eventId + "'");
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
            dbConnectionInstance.closeConnection(connection);
        }
        return inputStream;
    }
}
