package com.arttu.mvc.dao;

import com.arttu.mvc.model.Order;

public interface OrderDao extends BaseDao<Order> {
    void deleteById(int id);
    boolean editOrder(Order order);
}
