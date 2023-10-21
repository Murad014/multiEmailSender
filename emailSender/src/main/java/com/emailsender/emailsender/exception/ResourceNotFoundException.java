package com.emailsender.emailsender.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String resource) {
        super(String.format("%s resouce not found", resource));
    }
}
