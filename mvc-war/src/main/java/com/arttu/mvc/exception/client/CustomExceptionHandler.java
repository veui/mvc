package com.arttu.mvc.exception.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ClientIsNotUniqueException.class)
    protected ResponseEntity<ClientErrorMessage> handleClientIsNotUniqueException() {
        return new ResponseEntity<>(new ClientErrorMessage("Client is not unique"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailIsNotUniqueException.class)
    protected ResponseEntity<ClientErrorMessage> handleEmailIsNotUniqueException() {
        return new ResponseEntity<>(new ClientErrorMessage("Email is not unique"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    protected ResponseEntity<ClientErrorMessage> handleClientNotFoundException() {
        return new ResponseEntity<>(new ClientErrorMessage("Client not found"),
                HttpStatus.NOT_FOUND);
    }
}
