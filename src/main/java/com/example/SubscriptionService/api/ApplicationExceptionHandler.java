package com.example.SubscriptionService.api;

import com.example.SubscriptionService.service.exception.ApplicationException;
import com.example.SubscriptionService.service.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ExceptionDto> handle(ApplicationException exception) {
        return ResponseEntity.internalServerError().body(new ExceptionDto(exception.getMessage()));
    }
}
