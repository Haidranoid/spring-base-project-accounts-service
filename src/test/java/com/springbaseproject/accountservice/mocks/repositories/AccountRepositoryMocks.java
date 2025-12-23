package com.springbaseproject.accountservice.mocks.repositories;


import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.accountservice.mocks.factories.AccountsMockFactory;
import com.springbaseproject.sharedstarter.entities.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryMocks {

    public static List<Account> getEmptyAccountsList() {
        return new ArrayList<>();
    }

    public static List<Account> getTwoAccounts() {
        List<Account> accounts = new ArrayList<>();

        accounts.add(AccountsMockFactory.accountOne());
        accounts.add(AccountsMockFactory.accountTwo());

        return accounts;
    }

    public static AccountResponseDto getAccountResponseDto() {
        return AccountsMockFactory.accountResponseDtoOne();
    }

    public static List<AccountResponseDto> getTwoAccountResponseDto() {
        List<AccountResponseDto> accountsDto = new ArrayList<>();

        accountsDto.add(AccountsMockFactory.accountResponseDtoOne());
        accountsDto.add(AccountsMockFactory.accountResponseDtoOne());

        return accountsDto;
    }
}
