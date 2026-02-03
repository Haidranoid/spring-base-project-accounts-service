package com.springbaseproject.accountservice.controllers;

import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.accountservice.common.dtos.AuthenticateAccountDto;
import com.springbaseproject.accountservice.common.dtos.CreateAccountDto;
import com.springbaseproject.accountservice.services.impl.AccountServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Tag(name = "Internal Account Controller", description = "Internal endpoints. Do NOT call directly from client applications.")
//@RequestMapping("/api/v1/internal/accounts")
@RequestMapping("/api/v1/internal/accounts")
@RestController
@RequiredArgsConstructor
public class InternalAccountController {

    private final AccountServiceImpl accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates an account")
    public AccountResponseDto createAccount(@RequestBody CreateAccountDto createAccountDto) {
        log.info("createAccount request body: {}", createAccountDto);

        var account = accountService.create(createAccountDto);

        log.info("createAccount request response: {}", account);
        return account;
    }

    @PostMapping("/authenticate-login")
    @Operation(summary = "Authenticate if the user exists, if so, return it.")
    public AccountResponseDto authenticateLogin(
            @RequestBody AuthenticateAccountDto authenticateAccountDto
    ) {
        log.info("authenticateLogin request body: {}", authenticateAccountDto);

        var account = accountService.authenticateAccount(authenticateAccountDto);

        log.info("authenticateLogin request response: {}", account);
        return account;
    }
}

