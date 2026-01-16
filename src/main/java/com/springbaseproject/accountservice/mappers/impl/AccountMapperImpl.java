package com.springbaseproject.accountservice.mappers.impl;

import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.accountservice.common.dtos.CreateAccountDto;
import com.springbaseproject.accountservice.mappers.AccountMapper;
import com.springbaseproject.sharedstarter.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {
    @Override
    public Account toEntity(CreateAccountDto dto) {
        return Account.builder()
                .username(dto.username())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .password(dto.password())
                .role(dto.role())
                .build();
    }

    @Override
    public AccountResponseDto toDto(Account account) {
        return AccountResponseDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .role(account.getRole())
                .build();
    }
}