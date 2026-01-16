package com.springbaseproject.accountservice.unit.services;

import com.springbaseproject.accountservice.mappers.impl.AccountMapperImpl;
import com.springbaseproject.accountservice.fixtures.AccountFixtures;
import com.springbaseproject.accountservice.repositories.AccountRepository;
import com.springbaseproject.accountservice.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountMapperImpl accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void findAll_whenNoAccounts_shouldReturnAnEmptyList() {
        // Arrange
        when(accountRepository.findAll())
                .thenReturn(AccountFixtures.getEmptyAccountsList());

        // Act
        var accounts = accountService.findAll();

        // Assert
        assertNotNull(accounts);
        assertEquals(0, accounts.size());
    }

    @Test
    void findAll_whenAccountsAreEqualToTwo_shouldReturnTwoAccounts() {
        var mockedAccounts = AccountFixtures.getTwoAccounts();
        when(accountRepository.findAll()).thenReturn(mockedAccounts);

        mockedAccounts.forEach(account -> {
            when(accountMapper.toDto(account))
                    .thenReturn(AccountFixtures.getAccountResponseDto());
        });

        var accounts = accountService.findAll();

        assertNotNull(accounts.getFirst());
        assertEquals(2, accounts.size());
    }

    @Test
    void findById_whenAccountExists_shouldReturnDto() {
        when(accountRepository.findById(1L))
                .thenReturn(AccountFixtures.accountOptional());
        when(accountMapper.toDto(any()))
                .thenReturn(AccountFixtures.getAccountResponseDto());

        var accountById = accountService.findById(1L);

        assertNotNull(accountById);
    }
}