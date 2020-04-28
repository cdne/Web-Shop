package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.List;
import java.util.Map;

public interface CartDao {

    void add(Product product);
    void remove(Product product);
    Map<Product, Integer> getCartContents();
    int getCartNumberOfProducts();
    void eraseMe();


}
