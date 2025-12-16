package com.springbaseproject.accountservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiError {
    private final HttpStatus httpStatus;
    private final String message;
    private final LocalDateTime timestamp;

    public ApiError(String message, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
