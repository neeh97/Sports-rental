package com.group1.sports_rental.ProductPage;

import com.group1.sports_rental.Advertisement.AbstractFactory;
import com.group1.sports_rental.Advertisement.ConcreteFactory;
import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import com.group1.sports_rental.Utils.DbConnectionSingleton;
import org.springframework.stereotype.Repository;
import java.io.InputStream;
import java.sql.*;

@Repository
public class ProductPageDao implements IProductPageDao
{
    DbConnectionSingleton dbConnection= DbConnectionSingleton.getInstance();

    public ProductAdvertisement displayProduct(String productId)
    {
        AbstractFactory factory = new ConcreteFactory();
        ProductAdvertisement productAdvertisement = factory.createProduct();
        Connection connection = dbConnection.getConnection();
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE product_id='" + productId + "'");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                productAdvertisement.setItemImage(resultSet.getBytes("product_image"));
                productAdvertisement.setProductName(resultSet.getString("product_name"));
                productAdvertisement.setProductDescription(resultSet.getString("product_description"));
                productAdvertisement.setBrand(resultSet.getString("brand"));
                productAdvertisement.setRentalCost(resultSet.getFloat("rental_cost"));
                productAdvertisement.setStartDate(resultSet.getDate("available_from_date"));
                productAdvertisement.setEndDate(resultSet.getDate("available_to_date"));
                productAdvertisement.setCategory(resultSet.getString("category"));
                productAdvertisement.setSportsType(resultSet.getString("sports_type"));
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
        return productAdvertisement;
    }

    public InputStream getProductImage(String productId)
    {
        Connection connection = dbConnection.getConnection();
        InputStream inputStream = null;
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE product_id='" + productId + "'");
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                inputStream = rs.getBinaryStream("product_image");
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
        return inputStream;
    }
}
