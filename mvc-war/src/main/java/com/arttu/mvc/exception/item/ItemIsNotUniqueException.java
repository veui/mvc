package com.arttu.mvc.exception.item;

public class ItemIsNotUniqueException extends RuntimeException {

    public ItemIsNotUniqueException() {}

    public ItemIsNotUniqueException(String message) {
        super(message);
    }
}
