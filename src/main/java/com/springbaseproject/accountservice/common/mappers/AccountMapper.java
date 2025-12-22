package com.springbaseproject.accountservice.common.mappers;

import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.accountservice.common.dtos.CreateAccountDto;
import com.springbaseproject.sharedstarter.entities.Account;
import com.springbaseproject.sharedstarter.mappers.DtoToEntityMapper;
import com.springbaseproject.sharedstarter.mappers.EntityToDtoMapper;

public interface AccountMapper extends
        EntityToDtoMapper<Account, AccountResponseDto>,
        DtoToEntityMapper<CreateAccountDto, Account> {

}
