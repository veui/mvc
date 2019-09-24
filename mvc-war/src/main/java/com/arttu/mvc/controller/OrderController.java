package com.arttu.mvc.controller;

import com.arttu.mvc.exception.client.ClientNotFoundException;
import com.arttu.mvc.exception.item.ItemNotFoundException;
import com.arttu.mvc.exception.order.OrderNotFoundException;
import com.arttu.mvc.model.Client;
import com.arttu.mvc.model.Item;
import com.arttu.mvc.model.Order;
import com.arttu.mvc.service.ClientService;
import com.arttu.mvc.service.ItemService;
import com.arttu.mvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {

    private OrderService orderService;
    private ClientService clientService;
    private ItemService itemService;

    @Autowired
    public OrderController(OrderService orderService, ClientService clientService, ItemService itemService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.itemService = itemService;
    }

    @GetMapping("/order")
    public ModelAndView department() {
        ModelAndView modelAndView = new ModelAndView("order/order");
        List<Order> orderList = orderService.findAll();
        modelAndView.addObject("orderList", orderList);
        return modelAndView;
    }

    @GetMapping("/order/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("order/addOrder");
        List<Item> itemList = itemService.findAll();
        List<Client> clientList = clientService.findAll();
        if (itemList.isEmpty()) throw new ItemNotFoundException("Item not found");
        if (clientList.isEmpty()) throw new ClientNotFoundException("Client not found");
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("clientList", clientList);
        return modelAndView;
    }

    @GetMapping("/order/add/{id}")
    public ModelAndView add(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("order/addOrder");
        Item item = itemService.findById(id);
        List<Item> itemList = itemService.findAll();
        List<Client> clientList = clientService.findAll();
        if (item.getId() == 0) throw new ItemNotFoundException("Item not found");
        if (itemList.isEmpty()) throw new ItemNotFoundException("Item not found");
        if (clientList.isEmpty()) throw new ClientNotFoundException("Client not found");
        modelAndView.addObject("item", item);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("clientList", clientList);
        return modelAndView;
    }

    @GetMapping(value = "/order/edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView("order/editOrder");
        List<Item> itemList = itemService.findAll(); //TODO potent npe
        List<Client> clientList = clientService.findAll();
        if (itemList.isEmpty()) throw new ItemNotFoundException("Item not found");
        if (clientList.isEmpty()) throw new ClientNotFoundException("Client not found");
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("clientList", clientList);
        return modelAndView;
    }

    @GetMapping(value = "/order/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("order/editOrder");
        List<Item> itemList = itemService.findAll();
        List<Client> clientList = clientService.findAll();
        Order order = orderService.findById(id);
        if (itemList.isEmpty()) throw new ItemNotFoundException("Item not found");
        if (clientList.isEmpty()) throw new ClientNotFoundException("Client not found");
        if (order.getId() == 0) throw new OrderNotFoundException("Order not found");
        modelAndView.addObject("clientList", clientList);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("orderList", order);
        return modelAndView;
    }
}
