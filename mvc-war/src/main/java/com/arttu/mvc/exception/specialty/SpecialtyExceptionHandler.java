package com.arttu.mvc.exception.specialty;

import com.arttu.mvc.controller.SpecialtyController;
import com.arttu.mvc.controller.rest.SpecialtyRestController;
import com.arttu.mvc.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(assignableTypes = {SpecialtyController.class,
        SpecialtyRestController.class})
public class SpecialtyExceptionHandler {

    @ExceptionHandler(SpecialtyIsNotUniqueException.class)
    public ResponseEntity<ErrorMessage> handleSpecialtyIsNotUniqueException() {
        return new ResponseEntity<>(new ErrorMessage("Specialty is not unique"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SpecialtyNotFoundException.class)
    public ModelAndView handleSpecialtyNotFoundException(SpecialtyNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("msg", ex.getMessage());
        return modelAndView;
    }
}
