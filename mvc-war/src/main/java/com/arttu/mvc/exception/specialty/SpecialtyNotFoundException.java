package com.arttu.mvc.exception.specialty;

public class SpecialtyNotFoundException extends RuntimeException {

    public SpecialtyNotFoundException() {}

    public SpecialtyNotFoundException(String message) {
        super(message);
    }
}
