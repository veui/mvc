package com.arttu.mvc.exception.department;

import com.arttu.mvc.controller.DepartmentController;
import com.arttu.mvc.controller.rest.DepartmentRestController;
import com.arttu.mvc.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(assignableTypes = {DepartmentController.class, DepartmentRestController.class})
public class DepartmentExceptionHandler {

    @ExceptionHandler(DepartmentIsNotUniqueException.class)
    protected ResponseEntity<ErrorMessage> handleDepartmentIsNotUniqueException() {
        return new ResponseEntity<>(new ErrorMessage("Department title is not unique"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    protected ModelAndView handleDepartmentNotFoundException(DepartmentNotFoundException exc) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("msg", exc.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({DepartmentNotFoundRestException.class})
    protected ResponseEntity<?> handleDepartmentNotFoundRestException() {
        return new ResponseEntity<>(new ErrorMessage("Department not found"),
                HttpStatus.NOT_FOUND);
    }
}
