package com.example.SubscriptionService.service;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(long id) {
        super("Cannot find subscription with id " + id);
    }
}
