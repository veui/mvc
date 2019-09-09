package com.arttu.mvc.exception.department;

public class DepartmentNotFoundException extends RuntimeException{

    public DepartmentNotFoundException() {}

    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
