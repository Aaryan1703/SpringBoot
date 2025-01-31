package com.employeeapicrud.exception;

public class EmployeeDatabaseEmptyException extends RuntimeException {
    public EmployeeDatabaseEmptyException(String message) {
        super(message);
    }
}

