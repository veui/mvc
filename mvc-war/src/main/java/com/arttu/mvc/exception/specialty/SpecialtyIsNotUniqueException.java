package com.arttu.mvc.exception.specialty;

public class SpecialtyIsNotUniqueException extends RuntimeException {

    public SpecialtyIsNotUniqueException() {}

    public SpecialtyIsNotUniqueException(String message) {
        super(message);
    }
}
