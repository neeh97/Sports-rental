package com.group1.sports_rental.GroundPage;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class GroundPageService implements IGroundPageService
{
    static GroundPageService instance = null;

    public static GroundPageService instance()
    {
        if(instance == null)
        {
            instance = new GroundPageService();
        }
        return instance;
    }

    public GroundAdvertisement displayGround(String groundId, IGroundPageDao groundPageDao)
    {
        return groundPageDao.displayGround(groundId);
    }

    public ByteArrayOutputStream displayGroundImage(String groundId, IGroundPageDao groundPageDao)
    {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try
        {
            InputStream inputStream = groundPageDao.getGroundImage(groundId);
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
