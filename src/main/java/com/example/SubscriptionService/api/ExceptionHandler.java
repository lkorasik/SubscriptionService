package com.example.SubscriptionService.api;

import com.example.SubscriptionService.service.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ExceptionDto> handle(UserNotFoundException exception) {
        return ResponseEntity.internalServerError().body(new ExceptionDto(exception.getMessage()));
    }
}
