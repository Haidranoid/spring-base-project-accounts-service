package com.springbaseproject.accountservice.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException() {
        super("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }
}