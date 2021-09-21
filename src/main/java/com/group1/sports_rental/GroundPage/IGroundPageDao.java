package com.group1.sports_rental.GroundPage;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;

import java.io.InputStream;

public interface IGroundPageDao
{
    GroundAdvertisement displayGround(String groundId);
    InputStream getGroundImage(String groundId);
}
