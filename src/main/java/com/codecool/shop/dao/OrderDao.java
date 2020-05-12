package com.codecool.shop.dao;

import com.codecool.shop.model.ListItem;
import com.codecool.shop.model.Order;

import java.util.List;

public interface OrderDao {

    public int add(Order order);
    public void update(Order order);
    public Order getOrderById(int id);
    public List<ListItem> getItems(int userId);

}
