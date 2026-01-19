package com.springbaseproject.accountservice.mappers;

import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.accountservice.common.dtos.CreateAccountDto;
import com.springbaseproject.sharedstarter.entities.AccountEntity;
import com.springbaseproject.sharedstarter.mappers.DtoToEntityMapper;
import com.springbaseproject.sharedstarter.mappers.EntityToDtoMapper;

public interface AccountMapper extends
        EntityToDtoMapper<AccountEntity, AccountResponseDto>,
        DtoToEntityMapper<CreateAccountDto, AccountEntity> {

}
