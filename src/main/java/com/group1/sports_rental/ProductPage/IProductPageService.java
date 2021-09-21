package com.group1.sports_rental.ProductPage;

import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;

import java.io.ByteArrayOutputStream;

public interface IProductPageService
{
   ProductAdvertisement displayProduct(String productId, IProductPageDao productPageDao);
   ByteArrayOutputStream displayProductImage(String productId, IProductPageDao productPageDao);
}
