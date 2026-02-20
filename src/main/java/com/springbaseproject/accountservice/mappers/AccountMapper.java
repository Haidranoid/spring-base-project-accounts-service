package com.springbaseproject.accountservice.mappers;

import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.accountservice.common.dtos.CreateAccountDto;
import com.springbaseproject.sharedstarter.entities.AccountEntity;

public interface AccountMapper {
    AccountEntity toEntity(CreateAccountDto dto);
    AccountResponseDto toDto(AccountEntity account);
}
