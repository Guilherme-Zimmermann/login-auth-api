package com.example.loginauthapi.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException() {
        super("Resource not found.");
    }
}
