package com.example.SubscriptionService.service.exception;

public class SubscriptionNotFoundException extends ApplicationException {
    public SubscriptionNotFoundException(long id) {
        super("Cannot find subscription with id " + id);
    }
}
