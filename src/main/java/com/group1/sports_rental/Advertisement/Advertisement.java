package com.group1.sports_rental.Advertisement;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.Date;

public class Advertisement
{
    private String id;
    private float rentalCost;
    private Date startDate;
    private Date endDate;
    private Long userId;
    private byte[] itemImage;
    private String sportsType;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public byte[] getItemImage()
    {
        return itemImage;
    }

    public void setItemImage(byte[] itemImage)
    {
        this.itemImage = itemImage;
    }

    public float getRentalCost()
    {
        return rentalCost;
    }

    public void setRentalCost(float rentalCost)
    {
        this.rentalCost =  rentalCost;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getSportsType()
    {
        return sportsType;
    }

    public void setSportsType(String sportsType)
    {
        this.sportsType = sportsType;
    }

    public Boolean validateRentalCost()
    {
        if (this.rentalCost<0 || this.rentalCost==0.0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Integer validateDateRange()
    {
        return this.startDate.compareTo(this.endDate);
    }

    public Boolean validateImageUpload(MultipartFile imageUpload) throws IOException
    {
        if (imageUpload.isEmpty())
        {
            return false;
        }
        else
        {
            byte[] image = imageUpload.getBytes();
            setItemImage(image);
            return true;
        }
    }
}
