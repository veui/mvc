package com.arttu.mvc.controller.rest;

import com.arttu.mvc.exception.order.OrderNotFoundRestException;
import com.arttu.mvc.model.Order;
import com.arttu.mvc.service.OrderService;
import com.arttu.mvc.util.ValidationHelper;
import com.arttu.mvc.validator.OrderValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderResrController {

    private static final Logger LOGGER = LogManager.getLogger(OrderResrController.class);
    private final OrderService orderService;
    private final OrderValidator orderValidator;

    @Autowired
    public OrderResrController(OrderService orderService, OrderValidator orderValidator) {
        this.orderService = orderService;
        this.orderValidator = orderValidator;
    }

    @InitBinder
    public void dataBind(WebDataBinder dataBinder) {
        dataBinder.setValidator(orderValidator);
    }

    @PostMapping(value = "/order/edit", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Order order) {
        Map<String, Object> response = new HashMap<>();
        boolean orderEdited = orderService.editOrder(order);
        if (orderEdited) {
            orderService.edit(order);
            response.put("message", "OK");
        } else {
            throw new OrderNotFoundRestException();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/order/add", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> add(@RequestBody Order order, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        orderValidator.validate(order, result);
        if (result.hasErrors()) {
            LOGGER.error("Add method of OrderRestController class");
            ValidationHelper.validation(result);
        } else {
            orderService.add(order);
            response.put("message", "OK");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/order/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        Order order = orderService.findById(id);
        if (order == null) throw new OrderNotFoundRestException();
        else {
            orderService.deleteById(id);
            response.put("message", "OK");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "order/rest/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable int id) {
        Order order = orderService.findById(id);
        if (order == null) throw new OrderNotFoundRestException();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @GetMapping(value = "order/rest/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        List<Order> orders = orderService.findAll();
        if (orders.isEmpty()) throw new OrderNotFoundRestException();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
