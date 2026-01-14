package com.springbaseproject.accountservice.fixtures;

import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.sharedstarter.constants.Roles;
import com.springbaseproject.sharedstarter.entities.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountFixtures {

    public static List<Account> getEmptyAccountsList() {
        return new ArrayList<>();
    }

    public static Account adminAccount() {
        return Account.builder()
                .username("admin@email.com")
                .email("admin@email.com")
                .password("<PASSWORD>")
                .firstName("Steve")
                .lastName("Rogers")
                .role(Roles.ADMIN)
                .build();
    }

    public static Account managerAccount() {
        return Account.builder()
                .username("manager@email.com")
                .email("manager@email.com")
                .password("<PASSWORD>")
                .firstName("Black")
                .lastName("Widow")
                .role(Roles.MANAGER)
                .build();
    }

    public static Optional<Account> accountOptional() {
        return Optional.of(Account.builder()
                .username("admin@email.com")
                .email("admin@email.com")
                .password("<PASSWORD>")
                .firstName("Steve")
                .lastName("Rogers")
                .role(Roles.ADMIN)
                .build()
        );
    }

    public static AccountResponseDto accountResponseDtoOne() {
        return AccountResponseDto.builder()
                .id(1L)
                .username("admin@email.com")
                .email("admin@email.com")
                .firstName("Steve")
                .lastName("Rogers")
                .role(Roles.ADMIN)
                .build();
    }

    public static List<Account> getTwoAccounts() {
        List<Account> accounts = new ArrayList<>();

        accounts.add(AccountFixtures.adminAccount());
        accounts.add(AccountFixtures.managerAccount());

        return accounts;
    }

    public static AccountResponseDto getAccountResponseDto() {
        return AccountFixtures.accountResponseDtoOne();
    }

    public static List<AccountResponseDto> getTwoAccountResponseDto() {
        List<AccountResponseDto> accountsDto = new ArrayList<>();

        accountsDto.add(AccountFixtures.accountResponseDtoOne());
        accountsDto.add(AccountFixtures.accountResponseDtoOne());

        return accountsDto;
    }
}
