package com.arttu.mvc.controller;

import com.arttu.mvc.model.Order;
import com.arttu.mvc.service.ClientService;
import com.arttu.mvc.service.ItemService;
import com.arttu.mvc.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);
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
        modelAndView.addObject("orderList", orderService.findAll());
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping("/order/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("order/addOrder");
        modelAndView.addObject("itemList", itemService.findAll());
        modelAndView.addObject("clientList", clientService.findAll());
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping("/order/add/{id}")
    public ModelAndView add(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("order/addOrder");
        modelAndView.addObject("item", itemService.findById(id));
        modelAndView.addObject("itemList", itemService.findAll());
        modelAndView.addObject("clientList", clientService.findAll());
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @PostMapping(value = "/order/add", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> add(@RequestBody Order order) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        orderService.add(order);
        response.put("stat", 1);
        return new ResponseEntity<>(response, status);
    }

    @GetMapping(value = "/order/edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView("order/editOrder");
        modelAndView.addObject("itemList", itemService.findAll());
        modelAndView.addObject("clientList", clientService.findAll());
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }


    @GetMapping(value = "/order/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("order/editOrder");
        modelAndView.addObject("clientList", clientService.findAll());
        modelAndView.addObject("itemList", itemService.findAll());
        modelAndView.addObject("orderList", orderService.findById(id));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @PostMapping(value = "/order/edit", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Order order) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            orderService.edit(order);
            response.put("stat", 1);
        } catch (Exception e) {
            LOGGER.error(e);
            response.put("stat", 0);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, status);
    }

    @GetMapping(value = "/order/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        orderService.deleteById(id);
        return new ModelAndView("redirect:/order");
    }
}
