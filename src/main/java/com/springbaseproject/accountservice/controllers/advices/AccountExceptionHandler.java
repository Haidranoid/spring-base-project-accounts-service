package com.springbaseproject.accountservice.controllers.advices;

import com.springbaseproject.accountservice.common.errors.ApiError;
import com.springbaseproject.accountservice.common.exceptions.AccountNotFoundException;
import com.springbaseproject.accountservice.common.exceptions.InvalidCredentialsException;
import com.springbaseproject.accountservice.common.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

//@RestControllerAdvice(basePackages = "com.springbaseproject.accountservice.controllers.publics")
public class AccountExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleInvalidCredentials(InvalidCredentialsException ex) {
        return ApiError.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleUnauthorized(UnauthorizedException ex) {
        return ApiError.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleAccountNotFound(AccountNotFoundException ex) {
        return ApiError.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

}
