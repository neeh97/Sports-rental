package com.group1.sports_rental.Advertisement.ProductAdvertisement;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.group1.sports_rental.Utils.DbConnectionSingleton;
import org.springframework.util.StringUtils;

@Repository
public class ProductAdvertisementDao implements IProductAdvertisementDao
{
    DbConnectionSingleton databaseInstance= DbConnectionSingleton.getInstance();

    static ProductAdvertisementDao instance = null;

    public static ProductAdvertisementDao instance()
    {
        if (instance == null)
        {
            instance = new ProductAdvertisementDao();
        }
        return instance;
    }

    public void insertProductData(ProductAdvertisement productAdvertisement)
    {
        UUID uuid= UUID.randomUUID();
        String uniqueProductId = "pr_"+uuid.toString().replace("-","");
        Connection connection = null;
        try
        {
            String insertQuery = "Insert into product" +
                    "(product_id,product_name,product_description,brand,rental_cost,available_from_date," +
                    "available_to_date,category,sports_type,userId,product_image)" +
                    " Values(?,?,?,?,?,?,?,?,?,?,?);";
            connection = databaseInstance.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, uniqueProductId);
            preparedStatement.setString(2, productAdvertisement.getProductName());
            preparedStatement.setString(3, productAdvertisement.getProductDescription());
            preparedStatement.setString(4, productAdvertisement.getBrand());
            preparedStatement.setFloat(5, productAdvertisement.getRentalCost());
            preparedStatement.setDate(6, productAdvertisement.getStartDate());
            preparedStatement.setDate(7, productAdvertisement.getEndDate());
            preparedStatement.setString(8, productAdvertisement.getCategory());
            preparedStatement.setString(9, productAdvertisement.getSportsType());
            preparedStatement.setLong(10, productAdvertisement.getUserId());
            preparedStatement.setBytes(11, productAdvertisement.getItemImage());
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

    public List<ProductAdvertisement> filterProducts(String city, String location, String category, String sportsType)
    {
        List<ProductAdvertisement> productAdvertisementList = new ArrayList<>();
        Connection connection = databaseInstance.getConnection();
        try
        {
            String filterQuery = "SELECT * FROM product WHERE userId IN (SELECT userId FROM user WHERE city = '" + city + "')";
            if (StringUtils.hasText(location)) {
                filterQuery = "SELECT * FROM product WHERE userId IN (SELECT userId FROM user WHERE city = '" + city + "' AND address = '" + location + "')";
            }
            if (StringUtils.hasText(category)) {
                filterQuery += " AND category='" + category + "'";
            }
            if (StringUtils.hasText(sportsType)) {
                filterQuery += " AND sports_type='" + sportsType + "'";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(filterQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            productAdvertisementList = convertResultSet(resultSet);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            databaseInstance.closeConnection(connection);
        }
        return productAdvertisementList;
    }

    public List<ProductAdvertisement> getDefaultSearchProducts(String city)
    {
        Connection connection = databaseInstance.getConnection();
        List<ProductAdvertisement> productAdvertisementsList = new ArrayList<>();
        try
        {
            String searchQuery = "SELECT * FROM product WHERE userId IN (SELECT userId from user WHERE city = '"+city+"' )";
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            productAdvertisementsList =convertResultSet(resultSet);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            databaseInstance.closeConnection(connection);
        }
        return productAdvertisementsList;
    }

    public List<ProductAdvertisement> searchProductName(String city, String name)
    {
        List<ProductAdvertisement> productAdvertisementList = new ArrayList<>();
        Connection connection = databaseInstance.getConnection();
        try
        {
            String searchQuery = "SELECT * FROM product WHERE userId IN (SELECT userId from user WHERE city = '" + city + "') AND product_name LIKE '" + name + "%'";
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            productAdvertisementList = convertResultSet(resultSet);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            databaseInstance.closeConnection(connection);
        }
        return productAdvertisementList;
    }

    public ProductAdvertisement findOne(String productId)
    {
        ProductAdvertisement productAdvertisement = null;
        Connection connection = null;
        AbstractFactory factory = new ConcreteFactory();
        try
        {
            connection = databaseInstance.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE product_id = '"+productId+"'");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            productAdvertisement = factory.createProduct();
            productAdvertisement.setId(productId);
            productAdvertisement.setProductName(resultSet.getString("product_name"));
            productAdvertisement.setProductDescription(resultSet.getString("product_description"));
            productAdvertisement.setBrand(resultSet.getString("brand"));
            productAdvertisement.setCategory(resultSet.getString("category"));
            productAdvertisement.setSportsType(resultSet.getString("sports_type"));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            databaseInstance.closeConnection(connection);
        }
        return productAdvertisement;
    }

    private List<ProductAdvertisement> convertResultSet(ResultSet resultSet) throws SQLException
    {
        List<ProductAdvertisement> allProductsFromResultSet = new ArrayList<>();
        AbstractFactory factory = new ConcreteFactory();
        if (resultSet != null) {
            while (resultSet.next())
            {
                ProductAdvertisement productAdvertisement = factory.createProduct();
                productAdvertisement.setId(resultSet.getString("product_id"));
                productAdvertisement.setProductName(resultSet.getString("product_name"));
                productAdvertisement.setProductDescription(resultSet.getString("product_description"));
                productAdvertisement.setCategory(resultSet.getString("category"));
                productAdvertisement.setBrand(resultSet.getString("brand"));
                productAdvertisement.setSportsType(resultSet.getString("sports_type"));
                allProductsFromResultSet.add(productAdvertisement);
            }
        }
        return allProductsFromResultSet;
    }
}
