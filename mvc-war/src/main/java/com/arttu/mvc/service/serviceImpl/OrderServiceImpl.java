package com.arttu.mvc.service.serviceImpl;

import com.arttu.mvc.dao.OrderDao;
import com.arttu.mvc.model.Order;
import com.arttu.mvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void add(Order order) {
        orderDao.add(order);
    }

    @Override
    public void delete(Order order) {
        orderDao.delete(order);
    }

    @Override
    public void edit(Order order) {
        orderDao.edit(order);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Order findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public void deleteById(int id) {
        orderDao.deleteById(id);
    }
}
