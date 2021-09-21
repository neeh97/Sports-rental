package com.group1.sports_rental.Advertisement.ProductAdvertisement;

import com.group1.sports_rental.Advertisement.Advertisement;

public class ProductAdvertisement extends Advertisement
{
    private String productName;
    private String productDescription;
    private String brand;
    private String category;

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductDescription()
    {
        return productDescription;
    }

    public void setProductDescription(String productDescription)
    {
        this.productDescription = productDescription;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public Boolean validateProductName()
    {
        if (this.productName == null || this.productName.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Boolean validateProductDescription()
    {
        if (this.productDescription == null || this.productDescription.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
