package com.group1.sports_rental.Advertisement.EventAdvertisement;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import com.group1.sports_rental.Utils.DbConnectionSingleton;
import org.springframework.util.StringUtils;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventAdvertisementDao implements IEventAdvertisementDao
{
    DbConnectionSingleton databaseInstance= DbConnectionSingleton.getInstance();

    static EventAdvertisementDao instance = null;

    public static EventAdvertisementDao instance()
    {
        if (instance == null)
        {
            instance = new EventAdvertisementDao();
        }
        return instance;
    }

    public String fetchCity(String eventState, String eventCity)
    {
        Connection connection = databaseInstance.getConnection();
        String validCity = "";
        String stateLower = eventState.toLowerCase();
        String cityLower = eventCity.toLowerCase();
        String citySpaceRemoved = cityLower.replace(" ","");
        try
        {
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

    public void insertEventRecord(EventAdvertisement eventAdvertisement)
    {
        Connection connection = databaseInstance.getConnection();
        UUID uuid= UUID.randomUUID();
        String uniqueEventId = "ev_"+uuid.toString().replace("-","");
        try
        {
            String insertQuery = " Insert into event" +
                    "(event_id, total_tickets, event_name, event_address, event_city, event_state, ticket_price, user_id, payment_status, event_date, start_time, end_time,image,tickets_left)"+
                    "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1,uniqueEventId);
            preparedStatement.setInt(2,eventAdvertisement.getTotalTickets());
            preparedStatement.setString(3, eventAdvertisement.getEventName());
            preparedStatement.setString(4, eventAdvertisement.getEventAddress());
            preparedStatement.setString(5,eventAdvertisement.getEventCity());
            preparedStatement.setString(6, eventAdvertisement.getEventState());
            preparedStatement.setFloat(7,eventAdvertisement.getTicketPrice());
            preparedStatement.setLong(8, eventAdvertisement.getUserId());
            preparedStatement.setString(9, eventAdvertisement.getPaymentStatus());
            preparedStatement.setDate(10, eventAdvertisement.getEventDate());
            preparedStatement.setTime(11,eventAdvertisement.getStartTime());
            preparedStatement.setTime(12,eventAdvertisement.getEndTime());
            preparedStatement.setBytes(13, eventAdvertisement.getItemImage());
            preparedStatement.setInt(14,eventAdvertisement.getTotalTickets());
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

    public List<EventAdvertisement> applyFilters(String city, String location, String minimumPrice, String maximumPrice)
    {
        List<EventAdvertisement> eventAdvertisementList = new ArrayList<>();
        Connection connection = databaseInstance.getConnection();
        try
        {
            String filterQuery = "SELECT * FROM event WHERE event_city = '" + city + "' AND event_date>='" + LocalDate.now() + "'";
            if (StringUtils.hasText(location)) {
                filterQuery += " AND event_address='" + location + "'";
            }
            if (StringUtils.hasText(minimumPrice)) {
                filterQuery += " AND ticket_price>=" + minimumPrice;
            }
            if (StringUtils.hasText(maximumPrice)) {
                filterQuery += " AND ticket_price<=" + maximumPrice;
            }
            PreparedStatement preparedStatement = connection.prepareStatement(filterQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            eventAdvertisementList = convertResultSet(resultSet);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            databaseInstance.closeConnection(connection);
        }
        return eventAdvertisementList;
    }

    public List<EventAdvertisement> getDefaultEvents(String city)
    {
        List<EventAdvertisement> eventAdvertisementList = new ArrayList<>();
        Connection connection = databaseInstance.getConnection();
        try
        {
            String searchQuery = "SELECT * FROM event WHERE event_city = '" + city + "' AND event_date>='" + LocalDate.now() + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            eventAdvertisementList = convertResultSet(resultSet);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            databaseInstance.closeConnection(connection);
        }
        return eventAdvertisementList;

    }

    public List<EventAdvertisement> searchEventName(String city, String searchText)
    {
        List<EventAdvertisement> eventAdvertisementList = new ArrayList<>();
        Connection connection = databaseInstance.getConnection();
        try
        {
            String searchQuery = "SELECT * FROM event WHERE event_city = '" + city + "' AND event_name LIKE '" + searchText + "%' AND event_date>='" + LocalDate.now() + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            eventAdvertisementList = convertResultSet(resultSet);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            databaseInstance.closeConnection(connection);
        }
        return eventAdvertisementList;
    }

    private List<EventAdvertisement> convertResultSet(ResultSet resultSet) throws SQLException
    {
        List<EventAdvertisement> eventAdvertisementList = new ArrayList<>();
        AbstractFactory factory = new ConcreteFactory();
        while (resultSet.next())
        {
            EventAdvertisement eventAdvertisement = factory.createEvent();
            eventAdvertisement.setEventId(resultSet.getString("event_id"));
            eventAdvertisement.setEventName(resultSet.getString("event_name"));
            eventAdvertisement.setEventDate(resultSet.getDate("event_date"));
            eventAdvertisement.setTicketPrice(resultSet.getFloat("ticket_price"));
            eventAdvertisement.setEventAddress(resultSet.getString("event_address"));
            eventAdvertisementList.add(eventAdvertisement);
        }
        return eventAdvertisementList;
    }

    public EventAdvertisement findOne(String eventId)
    {
        Connection connection = databaseInstance.getConnection();
        EventAdvertisement eventAdvertisement = null;
        AbstractFactory factory = new ConcreteFactory();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM event WHERE event_id='" + eventId + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                eventAdvertisement = factory.createEvent();
                eventAdvertisement.setEventDate(resultSet.getDate("event_date"));
                eventAdvertisement.setEventName(resultSet.getString("event_name"));
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
        return eventAdvertisement;
    }
}
