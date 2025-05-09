package com.example.SubscriptionService.service.exception;

public class IncorrectSubscriptionException extends ApplicationException{
    public IncorrectSubscriptionException() {
        super("Incorrect subscription");
    }
}
