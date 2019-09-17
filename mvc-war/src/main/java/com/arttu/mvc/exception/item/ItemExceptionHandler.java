package com.arttu.mvc.exception.item;

import com.arttu.mvc.controller.ItemController;
import com.arttu.mvc.controller.rest.ItemRestController;
import com.arttu.mvc.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(assignableTypes = {ItemController.class, ItemRestController.class})
public class ItemExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ModelAndView handleItemNotFoundException(ItemNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("msg", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(ItemIsNotUniqueException.class)
    public ResponseEntity<ErrorMessage> handleItemNotUniqueException() {
        return new ResponseEntity<>(new ErrorMessage("Item is not unique"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemNotFoundRestException.class)
    public ResponseEntity<ErrorMessage> handleItemNotFoundRestException() {
        return new ResponseEntity<>(new ErrorMessage("Item not found"), HttpStatus.NOT_FOUND);
    }
}
