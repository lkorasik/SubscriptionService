package com.example.SubscriptionService.service;

public class CannotCreateUserEmptyNameException extends RuntimeException {
    public CannotCreateUserEmptyNameException() {
        super("Cannot create user with empty name");
    }
}
