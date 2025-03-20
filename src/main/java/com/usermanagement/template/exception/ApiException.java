package com.usermanagement.template.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) { super(message); }
    public ApiException() { super("An error occurred"); }
}