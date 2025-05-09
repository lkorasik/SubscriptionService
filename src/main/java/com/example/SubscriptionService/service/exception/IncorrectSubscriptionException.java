package com.example.SubscriptionService.service.exception;

public class IncorrectSubscriptionException extends RuntimeException{
    public IncorrectSubscriptionException() {
        super("Incorrect subscription");
    }
}
