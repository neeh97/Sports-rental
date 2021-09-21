package com.group1.sports_rental.ProductPage;

import com.group1.sports_rental.Advertisement.ProductAdvertisement.ProductAdvertisement;

import java.io.InputStream;

public interface IProductPageDao
{
    ProductAdvertisement displayProduct(String productId);
    InputStream getProductImage(String productId);
}
