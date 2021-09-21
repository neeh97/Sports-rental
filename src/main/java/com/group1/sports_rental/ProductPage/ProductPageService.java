package com.group1.sports_rental.ProductPage;

import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ProductPageService implements IProductPageService
{
    static ProductPageService instance = null;

    public static ProductPageService instance()
    {
        if(instance == null)
        {
            instance = new ProductPageService();
        }
        return instance;
    }

    public ProductAdvertisement displayProduct(String productId, IProductPageDao productPageDao)
    {
        return productPageDao.displayProduct(productId);
    }

    public ByteArrayOutputStream displayProductImage(String productId, IProductPageDao productPageDao)
    {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try
        {
            InputStream inputStream = productPageDao.getProductImage(productId);
            if (inputStream == null)
            {
                return byteArrayOutputStream;
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] imageBuffer = new byte[inputStream.available()];
            Integer numberofBytes;
            while ((numberofBytes = inputStream.read(imageBuffer, 0, imageBuffer.length)) >= 0)
            {
                byteArrayOutputStream.write(imageBuffer, 0, numberofBytes);
            }
            inputStream.close();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return byteArrayOutputStream;
    }
}
