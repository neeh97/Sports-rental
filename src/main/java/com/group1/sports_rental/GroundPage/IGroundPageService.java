package com.group1.sports_rental.GroundPage;

import com.group1.sports_rental.Advertisement.GroundAdvertisement.GroundAdvertisement;

import java.io.ByteArrayOutputStream;

public interface IGroundPageService
{
  GroundAdvertisement displayGround(String groundId, IGroundPageDao groundPageDao);
  ByteArrayOutputStream displayGroundImage(String groundId, IGroundPageDao groundPageDao);
}
