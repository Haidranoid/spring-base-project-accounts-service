package com.springbaseproject.accountservice.controllers.publics;

import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.accountservice.common.dtos.ChangePasswordAccountDto;
import com.springbaseproject.accountservice.common.dtos.UpdateAccountDto;
import com.springbaseproject.accountservice.services.impl.AccountServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Slf4j
@Tag(name = "Account Controller")
@RequestMapping("/api/v1/accounts")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;
    private final Environment environment;

    @GetMapping
    public List<AccountResponseDto> getAllAccounts() {
        log.info("getAllAccounts request started");

        var accountList = accountService.findAll();

        log.info("getAllAccounts request response: [{}]", "...");
        return accountList;
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get an account by id")
    public AccountResponseDto getAccountById(@PathVariable Long userId) {
        log.info("getAccountById request: {}", userId);

        var account = accountService.findById(userId);

        log.info("getAccountById response: {}", account);
        return account;
    }

    @PatchMapping("/{userId}")
    public AccountResponseDto updateAccount(
            @PathVariable Long userId,
            @RequestBody UpdateAccountDto updateAccountDto
    ) {
        log.info("updateAccount request body: {}", updateAccountDto);

        var accountUpdated = accountService.update(userId, updateAccountDto);

        log.info("updateAccount request response: {}", accountUpdated);
        return accountUpdated;
    }


    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long userId) {
        log.info("deleteAccount request: {}", userId);

        accountService.delete(userId);

        log.info("deleteAccount request response: {}", userId);
    }


    @PatchMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordAccountDto changePasswordAccountDto) {
        log.info("changePassword request: {}", changePasswordAccountDto);

        //accountService.changePassword(changePasswordAccountDto);

        log.info("changePassword response: {}", "success");
    }
}

