package com.technicanApp.technicanApp.entity;

public class TechnicianNotFoundException extends RuntimeException{
    public TechnicianNotFoundException(String message) {
        super(message);
    }

    public TechnicianNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicianNotFoundException(Throwable cause) {
        super(cause);
    }
}
