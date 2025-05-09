package com.example.SubscriptionService.service.exception;

public class CannotCreateUserEmptyNameException extends RuntimeException {
    public CannotCreateUserEmptyNameException() {
        super("Cannot create user with empty name");
    }
}
