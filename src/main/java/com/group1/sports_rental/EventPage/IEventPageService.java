package com.group1.sports_rental.EventPage;

import com.group1.sports_rental.Advertisement.EventAdvertisement.EventAdvertisement;
import java.io.ByteArrayOutputStream;

public interface IEventPageService
{
    EventAdvertisement showEvent(String eventId, IEventPageDao eventPageDao);
    ByteArrayOutputStream displayEventImage(String eventId, IEventPageDao eventPageDao);
}
