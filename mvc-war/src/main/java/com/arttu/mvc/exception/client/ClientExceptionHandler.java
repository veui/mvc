package com.arttu.mvc.exception.client;

import com.arttu.mvc.controller.ClientController;
import com.arttu.mvc.controller.rest.ClientRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice(assignableTypes = {ClientController.class, ClientRestController.class})
@EnableWebMvc
public class ClientExceptionHandler {

    @ExceptionHandler(ClientIsNotUniqueRestException.class)
    protected ResponseEntity<ClientErrorMessage> handleClientIsNotUniqueException() {
        return new ResponseEntity<>(new ClientErrorMessage("Client is not unique"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailIsNotUniqueRestException.class)
    protected ResponseEntity<ClientErrorMessage> handleEmailIsNotUniqueException() {
        return new ResponseEntity<>(new ClientErrorMessage("Email is not unique"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundRestException.class)
    protected ResponseEntity<ClientErrorMessage> handleClientNotFoundRestException() {
        return new ResponseEntity<>(new ClientErrorMessage("Client not found"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    protected ModelAndView handleClientNotFoundException(ClientNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("msg", exception.getMessage());
        return modelAndView;
    }
}
