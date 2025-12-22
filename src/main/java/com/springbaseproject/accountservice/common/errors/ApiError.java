package com.springbaseproject.accountservice.common.errors;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {
    private final String message;
    private final LocalDateTime timestamp;
}

