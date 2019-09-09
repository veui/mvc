package com.arttu.mvc.exception.department;

public class DepartmentIsNotUniqueException extends RuntimeException {

    public DepartmentIsNotUniqueException() {}

    public DepartmentIsNotUniqueException(String message) {
        super(message);
    }
}
