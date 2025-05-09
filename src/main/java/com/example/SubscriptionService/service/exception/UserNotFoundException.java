package com.example.SubscriptionService.service.exception;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException(long id) {
        super("User with id " + id + " does not exists");
    }
}
