package com.arttu.mvc.service;

import com.arttu.mvc.model.Order;

import java.util.List;

public interface OrderService {
    void add(Order order);
    void delete(Order order);
    void edit(Order order);
    boolean editOrder(Order order);
    List<Order> findAll();
    Order findById(int id);

    void deleteById(int id);
}
