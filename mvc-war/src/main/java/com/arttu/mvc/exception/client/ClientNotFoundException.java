package com.arttu.mvc.exception.client;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {}

    public ClientNotFoundException(String message) {
        super(message);
    }
}
