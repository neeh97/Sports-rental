package com.group1.sports_rental.EventPage;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class EventPageService implements IEventPageService
{
    public EventAdvertisement showEvent(String eventId, IEventPageDao eventPageDao)
    {
        return eventPageDao.displayEvent(eventId);
    }

    public ByteArrayOutputStream displayEventImage(String eventId, IEventPageDao eventPageDao)
    {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try
        {
            InputStream inputStream = eventPageDao.getEventImage(eventId);
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
