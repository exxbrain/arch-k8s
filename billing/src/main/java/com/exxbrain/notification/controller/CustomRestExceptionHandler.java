package com.exxbrain.notification.controller;

import com.exxbrain.notification.ApplicationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ ApplicationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ApplicationException ex, WebRequest request) {
        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }
}
