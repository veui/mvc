package com.arttu.mvc.dao;

import com.arttu.mvc.model.Order;

import java.sql.SQLException;

public interface OrderDao extends BaseDao<Order> {
    void deleteById(int id);
}
