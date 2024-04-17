package com.example.realtimestreaming.Common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(Exception e) {
        int errorStatus = 500;
        int errorCode = 1000;
        String errorMessage = "BackendError - " + e.getMessage();
        System.out.println(e.getMessage());
        CustomErrorResponse errorResponse = new CustomErrorResponse(errorStatus, errorCode, errorMessage);
        System.out.println(errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(UserApplicationException.class)
    public ResponseEntity<CustomErrorResponse> handleUserApplicationException(UserApplicationException e) {
        ErrorCode errorCode = e.getErrorCode();
        CustomErrorResponse errorResponse = new CustomErrorResponse(errorCode.getCode(), errorCode.getStatus(), errorCode.getMessage());
        System.out.println(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }
}