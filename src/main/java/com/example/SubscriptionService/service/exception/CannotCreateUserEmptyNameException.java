package com.example.SubscriptionService.service.exception;

public class CannotCreateUserEmptyNameException extends ApplicationException {
    public CannotCreateUserEmptyNameException() {
        super("Cannot create user with empty name");
    }
}
