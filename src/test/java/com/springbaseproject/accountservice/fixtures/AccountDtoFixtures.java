package com.springbaseproject.accountservice.fixtures;

import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.accountservice.common.dtos.AuthenticateAccountDto;
import com.springbaseproject.accountservice.common.dtos.CreateAccountDto;
import com.springbaseproject.accountservice.common.dtos.UpdateAccountDto;
import com.springbaseproject.sharedstarter.constants.Roles;
import com.springbaseproject.sharedstarter.entities.AccountEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDtoFixtures {

    public static AccountResponseDto adminAccountResponseDto(Long id) {
        return AccountResponseDto.builder()
                .id(id)
                .username("admin")
                .email("admin@email.com")
                .firstName("Steve")
                .lastName("Rogers")
                .role(Roles.ADMIN)
                .build();
    }

    public static CreateAccountDto createAdminAccountDto() {
        return CreateAccountDto.builder()
                .username("admin")
                .firstName("Steve")
                .lastName("Rogers")
                .email("admin@email.com")
                .password("<PASSWORD>")
                .role(Roles.ADMIN)
                .build();
    }

    public static CreateAccountDto createManagerAccountDto() {
        return CreateAccountDto.builder()
                .username("manager")
                .firstName("Black")
                .lastName("Widow")
                .email("manager@email.com")
                .password("<PASSWORD>")
                .role(Roles.MANAGER)
                .build();
    }

    public static UpdateAccountDto updateAccountDtoOne() {
        return UpdateAccountDto.builder()
                .firstName("Steve")
                .lastName("Rogers")
                .email("manager@email.com")
                .password("<PASSWORD>")
                .role(Roles.ADMIN)
                .build();
    }

    public static AuthenticateAccountDto authenticateAdminAccountDto() {
        return AuthenticateAccountDto.builder()
                .username("admin")
                .password("<PASSWORD>")
                .build();
    }

    public static List<AccountResponseDto> twoAccountResponseDto() {
        List<AccountResponseDto> accountsDto = new ArrayList<>();

        accountsDto.add(AccountDtoFixtures.adminAccountResponseDto(1L));
        accountsDto.add(AccountDtoFixtures.adminAccountResponseDto(2L));

        return accountsDto;
    }
}
