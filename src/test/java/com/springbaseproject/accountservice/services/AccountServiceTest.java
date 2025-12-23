package com.springbaseproject.accountservice.services;


import com.springbaseproject.accountservice.common.mappers.AccountMapper;
import com.springbaseproject.accountservice.common.mappers.impl.AccountMapperImpl;
import com.springbaseproject.accountservice.mocks.repositories.AccountRepositoryMocks;
import com.springbaseproject.accountservice.repositories.AccountRepository;
import com.springbaseproject.accountservice.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
//@DisplayName("Account Service Test")
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountMapperImpl accountMapper;
    //private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void whenAccountRepository_findAll_shouldReturnAnEmptyList() {
        when(accountRepository.findAll()).thenReturn(AccountRepositoryMocks.getEmptyAccountsList());

        var accounts = accountService.findAll();

        assertNotNull(accounts);
        assertEquals(0, accounts.size());
    }

    @Test
    void whenAccountRepository_findAll_shouldReturnTwoAccounts() {
        var mockedAccounts = AccountRepositoryMocks.getTwoAccounts();
        when(accountRepository.findAll()).thenReturn(mockedAccounts);

        mockedAccounts.forEach(account -> {
            when(accountMapper.toDto(account)).thenReturn(AccountRepositoryMocks.getAccountResponseDto());
        });

        var accounts = accountService.findAll();

        assertEquals(2, accounts.size());
    }

    @Test
    public void whenAccountRepository_findAll_shouldReturnTwoAccounts_v2() {
        var mockedAccounts = AccountRepositoryMocks.getTwoAccounts();
        when(accountRepository.findAll()).thenReturn(mockedAccounts);

        var accounts = accountService.findAll();

        assertEquals(2, accounts.size());
    }
}