package com.group1.sports_rental.EventPage;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import java.io.InputStream;

public interface IEventPageDao
{
    EventAdvertisement displayEvent(String eventId);
    InputStream getEventImage(String eventId);
}
