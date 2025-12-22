package com.springbaseproject.accountservice.common.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException() {
        super("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }
}