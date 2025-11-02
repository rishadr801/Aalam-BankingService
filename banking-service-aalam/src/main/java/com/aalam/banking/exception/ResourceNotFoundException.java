package com.aalam.banking.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
    	super(msg); 
    }
}
