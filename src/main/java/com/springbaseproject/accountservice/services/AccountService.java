package com.springbaseproject.accountservice.services;

import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.accountservice.common.dtos.CreateAccountDto;
import com.springbaseproject.accountservice.common.dtos.UpdateAccountDto;
import com.springbaseproject.sharedstarter.services.BaseService;
import com.springbaseproject.sharedstarter.services.CreateService;
import com.springbaseproject.sharedstarter.services.UpdateService;

public interface AccountService
        extends BaseService<AccountResponseDto, Long>,
        CreateService<AccountResponseDto, CreateAccountDto>,
        UpdateService<AccountResponseDto, UpdateAccountDto, Long>
{ }
