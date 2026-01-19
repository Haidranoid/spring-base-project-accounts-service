package com.springbaseproject.accountservice.fixtures;

import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.accountservice.common.dtos.CreateAccountDto;
import com.springbaseproject.accountservice.mappers.impl.AccountMapperImpl;
import com.springbaseproject.sharedstarter.constants.Roles;
import com.springbaseproject.sharedstarter.entities.AccountEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountFixtures {

    static AccountMapperImpl accountMapper = new AccountMapperImpl();

    public static List<AccountEntity> getEmptyAccountsList() {
        return new ArrayList<>();
    }

    public static AccountEntity adminAccount() {
        return AccountEntity.builder()
                .id(1L)
                .username("admin@email.com")
                .firstName("Steve")
                .lastName("Rogers")
                .email("admin@email.com")
                .password("<PASSWORD>")
                .role(Roles.ADMIN)
                .build();
    }

    public static AccountEntity managerAccount() {
        return AccountEntity.builder()
                .id(2L)
                .username("manager@email.com")
                .firstName("Black")
                .lastName("Widow")
                .email("manager@email.com")
                .password("<PASSWORD>")
                .role(Roles.MANAGER)
                .build();
    }

    public static CreateAccountDto createAdminAccountDto() {
        return CreateAccountDto.builder()
                .username("admin@email.com")
                .firstName("Steve")
                .lastName("Rogers")
                .email("admin@email.com")
                .password("<PASSWORD>")
                .role(Roles.ADMIN)
                .build();
    }

    public static CreateAccountDto createManagerAccountDto() {
        return CreateAccountDto.builder()
                .username("manager@email.com")
                .firstName("Black")
                .lastName("Widow")
                .email("manager@email.com")
                .password("<PASSWORD>")
                .role(Roles.MANAGER)
                .build();
    }

    public static Optional<AccountEntity> accountOptional() {
        return Optional.of(AccountEntity.builder()
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

    public static List<AccountEntity> getTwoAccounts() {
        List<AccountEntity> accounts = new ArrayList<>();

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
