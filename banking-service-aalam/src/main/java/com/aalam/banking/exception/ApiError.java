package com.aalam.banking.exception;

import java.time.LocalDateTime;

public class ApiError {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    public ApiError(String message) { this.message = message; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getMessage() { return message; }
}
