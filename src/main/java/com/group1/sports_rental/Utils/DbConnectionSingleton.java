package com.group1.sports_rental.Utils;

import java.sql.*;

public class DbConnectionSingleton
{
    private static DbConnectionSingleton databaseInstance;

    private static String DB_URL = null;
    private static String DB_USERNAME = null;
    private static String DB_PASSWORD = null;

    private DbConnectionSingleton()
    {
        ApplicationConfiguration applicationConfiguration = ApplicationConfiguration.instance();
        DB_URL = applicationConfiguration.getDATABASE_URL();
        DB_USERNAME = applicationConfiguration.getDATABASE_USERNAME();
        DB_PASSWORD = applicationConfiguration.getDATABASE_PASSWORD();
    }
    public static DbConnectionSingleton getInstance()
    {
        if(databaseInstance == null)
        {
            databaseInstance = new DbConnectionSingleton();
        }
        return databaseInstance;
    }

    public Connection getConnection()
    {
        Connection connection = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        }
        catch (SQLException | ClassNotFoundException exceptionMessage)
        {
            exceptionMessage.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection)
    {
        try
        {
            if (connection != null && !connection.isClosed())
            {
                connection.close();
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
