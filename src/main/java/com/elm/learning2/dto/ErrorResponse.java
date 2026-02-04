package com.elm.learning2.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ErrorResponse {

    private final int status;
    private final String message;
    private final Map<String, String> errors;
    private final LocalDateTime timestamp;

    // Constructor for non-validation errors
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.errors = null;
        this.timestamp = LocalDateTime.now();
    }

    // Constructor for validation errors
    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }

}
