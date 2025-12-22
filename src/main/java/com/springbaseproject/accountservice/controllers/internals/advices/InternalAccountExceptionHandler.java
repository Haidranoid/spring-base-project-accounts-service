package com.springbaseproject.accountservice.controllers.internals.advices;

import com.springbaseproject.accountservice.common.errors.ApiError;
import com.springbaseproject.accountservice.common.exceptions.AccountNotFoundException;
import com.springbaseproject.accountservice.common.exceptions.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackages = "com.springbaseproject.accountservice.controllers.internals")
public class InternalAccountExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleInvalidCredentials(InvalidCredentialsException ex) {
        return ApiError.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
