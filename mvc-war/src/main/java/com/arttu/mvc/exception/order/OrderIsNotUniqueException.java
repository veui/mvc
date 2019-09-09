package com.arttu.mvc.exception.order;

public class OrderIsNotUniqueException extends RuntimeException {

    public OrderIsNotUniqueException() {}

    public OrderIsNotUniqueException(String message) {
        super(message);
    }
}
