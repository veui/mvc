package com.arttu.mvc.validator;

import com.arttu.mvc.model.Order;
import com.arttu.mvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderValidator implements Validator {

    private final OrderService orderService;

    @Autowired
    public OrderValidator(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Order.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Order order = (Order) o;

        List<Order> checkUniqueness = orderService.findAll();
        List<Order> result = checkUniqueness
                .stream()
                .filter(ord -> ord.equals(order))
                .collect(Collectors.toList());

        if (!result.isEmpty()) {
            errors.rejectValue("order", null, "order");
        }
    }
}
