package com.springbaseproject.accountservice.unit.services;

import com.springbaseproject.accountservice.mappers.AccountMapper;
import com.springbaseproject.accountservice.mappers.impl.AccountMapperImpl;
import com.springbaseproject.accountservice.fixtures.AccountFixtures;
import com.springbaseproject.accountservice.repositories.AccountRepository;
import com.springbaseproject.accountservice.services.impl.AccountServiceImpl;
import com.springbaseproject.sharedstarter.utils.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AccountMapperImpl accountMapper;
    @Mock
    private SecurityUtils securityUtils;

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
    void findAll_whenAccountsAreEqualToTwo_shouldReturnTwoAccountResponseDto() {
        var mockedAccounts = AccountFixtures.getTwoAccounts();
        when(accountRepository.findAll())
                .thenReturn(mockedAccounts);
        mockedAccounts.forEach(account -> {
            when(accountMapper.toDto(account))
                    .thenReturn(AccountFixtures.getAccountResponseDto());
        });

        var accounts = accountService.findAll();

        assertNotNull(accounts.getFirst());
        assertEquals(2, accounts.size());
    }

    @Test
    void findById_whenAccountExists_shouldReturnAccountResponseDto() {
        when(accountRepository.findById(1L))
                .thenReturn(AccountFixtures.accountOptional());
        when(accountMapper.toDto(any()))
                .thenReturn(AccountFixtures.getAccountResponseDto());

        var account = accountService.findById(1L);

        assertNotNull(account);
    }


    @Test
    void create_whenAccountIsPersisted_shouldReturnAccountResponseDto() {
        var adminAccountFixture = AccountFixtures.adminAccount();

        when(accountRepository.save(any()))
                .thenReturn(adminAccountFixture);
        when(accountMapper.toDto(adminAccountFixture))
                .thenReturn(AccountFixtures.getAccountResponseDto());

        var account = accountService.create(any());

        assertNotNull(account);
    }

    @Test
    void update_whenAccountIsUpdated_shouldReturnAccountResponseDto() {
        var accountOptionalFixture = AccountFixtures.accountOptional();
        var adminAccountFixture = AccountFixtures.adminAccount();

        when(accountRepository.findById(1L))
                .thenReturn(accountOptionalFixture);
        when(accountRepository.save(accountOptionalFixture.get()))
                .thenReturn(adminAccountFixture);
        when(accountMapper.toDto(any()))
                .thenReturn(AccountFixtures.accountResponseDtoOne());

        var account = accountService.update(1L, any());

        assertNotNull(account);
    }

    @Test
    void delete_whenAccountIsRemoved_shouldReturnNull() {
        var accountOptionalFixture = AccountFixtures.accountOptional();

        when(accountRepository.findById(1L))
                .thenReturn(accountOptionalFixture);

        accountService.delete(1L);

        verify(accountRepository, times(1)).delete(accountOptionalFixture.get());
    }
}