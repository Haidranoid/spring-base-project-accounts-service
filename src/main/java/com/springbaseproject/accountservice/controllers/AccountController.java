package com.springbaseproject.accountservice.controllers;

import com.springbaseproject.accountservice.dtos.*;
import com.springbaseproject.accountservice.services.impl.AccountServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/accounts")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @GetMapping
    @Tag(name = "Account Controller")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "5XX", description = "Server error"),
    })
    public List<AccountResponseDto> getAllAccounts() {
        logger.info("getAllAccounts request started");

        var accountList = accountService.findAll();
        var accountDtoList = accountList.stream().toList();

        logger.info("getAllAccounts request response: {}", accountDtoList.subList(0, 5));
        return accountDtoList;
    }

    @GetMapping("/{userId}")
    @Tag(name = "Account Controller")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "5XX", description = "Server error"),
    })
    public AccountResponseDto getAccountById(@PathVariable Long userId) {
        logger.info("getAccountById request: {}", userId);

        var account = accountService.findById(userId);

        logger.info("getAccountById response: {}", account);
        return account;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Tag(name = "Protected", description = "Do not access directly to these methods.")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "409", description = "Account already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public AccountResponseDto createAccount(@RequestBody CreateAccountDto createAccountDto) {
        logger.info("createAccount request body: {}", createAccountDto);

        var account = accountService.create(createAccountDto);

        logger.info("createAccount request response: {}", account);
        return account;
    }

    @PatchMapping("/{userId}")
    @Tag(name = "Account Controller")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "5XX", description = "Server error"),
    })
    public AccountResponseDto updateAccount(
            @PathVariable Long userId,
            @RequestBody UpdateAccountDto updateAccountDto
    ) {
        logger.info("updateAccount request body: {}", updateAccountDto);

        var accountUpdated = accountService.update(userId, updateAccountDto);

        logger.info("updateAccount request response: {}", accountUpdated);
        return accountUpdated;
    }


    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Tag(name = "Account Controller")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "5XX", description = "Server error"),
    })
    public void deleteAccount(@PathVariable Long userId) {
        logger.info("deleteAccount request: {}", userId);

        accountService.delete(userId);

        logger.info("deleteAccount request response: {}", userId);
    }

    @PostMapping("/authenticate-login")
    @Tag(name = "Protected", description = "Do not access directly to these methods.")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "5XX", description = "Server error"),
    })
    public AccountResponseDto authenticateLogin(
            @RequestBody AuthenticateAccountDto authenticateAccountDto
    ) {
        logger.info("authenticateLogin request body: {}", authenticateAccountDto);

        var account = accountService.authenticateAccount(authenticateAccountDto);

        logger.info("authenticateLogin request response: {}", account);
        return account;
    }

    // ============================================================================== //
    @PatchMapping("/change-password")
    @Tag(name = "Account Controller")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "5XX", description = "Server error"),
    })
    public void changePassword(@RequestBody ChangePasswordAccountDto changePasswordAccountDto) {
        logger.info("changePassword request: {}", changePasswordAccountDto);

        accountService.changePassword(changePasswordAccountDto);

        logger.info("changePassword response: {}", "success");
    }
}

