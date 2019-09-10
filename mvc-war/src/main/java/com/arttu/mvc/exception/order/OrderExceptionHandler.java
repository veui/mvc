package com.arttu.mvc.exception.order;

import com.arttu.mvc.controller.OrderController;
import com.arttu.mvc.controller.rest.OrderResrController;
import com.arttu.mvc.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(assignableTypes = {OrderController.class, OrderResrController.class})
public class OrderExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ModelAndView handleOrderNotFoundException(OrderNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("msg", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(OrderIsNotUniqueException.class)
    public ResponseEntity<ErrorMessage> handleOrderIsNotUniqueException() {
        return new ResponseEntity<>(new ErrorMessage("Order is not unique"),
                HttpStatus.BAD_REQUEST);
    }
}
