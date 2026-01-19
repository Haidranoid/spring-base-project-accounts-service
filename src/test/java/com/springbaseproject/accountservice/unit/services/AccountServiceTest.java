package com.springbaseproject.accountservice.unit.services;

import com.springbaseproject.accountservice.fixtures.AccountDtoFixtures;
import com.springbaseproject.accountservice.mappers.impl.AccountMapperImpl;
import com.springbaseproject.accountservice.fixtures.AccountEntityFixtures;
import com.springbaseproject.accountservice.repositories.AccountRepository;
import com.springbaseproject.accountservice.services.impl.AccountServiceImpl;
import com.springbaseproject.sharedstarter.utils.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        var emptyAccountList = AccountEntityFixtures.emptyAccountsList();

        when(accountRepository.findAll())
                .thenReturn(emptyAccountList);

        // Act
        var accounts = accountService.findAll();

        // Assert
        assertNotNull(accounts);
        assertEquals(0, accounts.size());
    }

    @Test
    void findAll_whenAccountsAreEqualToTwo_shouldReturnTwoAccountResponseDto() {
        var accountsPersisted = AccountEntityFixtures.twoAccountsPersisted();

        when(accountRepository.findAll())
                .thenReturn(accountsPersisted);
        when(accountMapper.toDto(accountsPersisted.get(0)))
                .thenReturn(AccountDtoFixtures.adminAccountResponseDto(1L));
        when(accountMapper.toDto(accountsPersisted.get(1)))
                .thenReturn(AccountDtoFixtures.adminAccountResponseDto(2L));
        /*
        mockedAccounts.forEach(account -> {
            when(accountMapper.toDto(account))
                    .thenReturn(AccountDtoFixtures.adminAccountResponseDto(1L));
        });
        */

        var accounts = accountService.findAll();

        assertEquals(2, accounts.size());
        assertEquals(1L, accounts.get(0).id());
        assertEquals(2L, accounts.get(1).id());
    }

    @Test
    void findById_whenAccountExists_shouldReturnAccountResponseDto() {
        var accountPersisted = AccountEntityFixtures.adminAccountPersisted(1L);
        var accountDto = AccountDtoFixtures.adminAccountResponseDto(1L);

        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(accountPersisted));
        when(accountMapper.toDto(accountPersisted))
                .thenReturn(accountDto);

        var accountFound = accountService.findById(1L);

        assertEquals(1L, accountFound.id());
        assertEquals("admin", accountFound.username());
    }


    @Test
    void create_whenAccountIsPersisted_shouldReturnAccountResponseDto() {
        var createAccountDto = AccountDtoFixtures.createAdminAccountDto();
        var accountEntity = AccountEntityFixtures.adminAccount();
        var accountPersisted = AccountEntityFixtures.adminAccountPersisted(1L);
        var accountDto = AccountDtoFixtures.adminAccountResponseDto(1L);

        when(accountMapper.toEntity(createAccountDto))
                .thenReturn(accountEntity);
        when(accountRepository.save(accountEntity))
                .thenReturn(accountPersisted);
        when(accountMapper.toDto(accountPersisted))
                .thenReturn(accountDto);

        var accountCreated = accountService.create(createAccountDto);

        assertNotNull(accountCreated);
        verify(accountMapper).toEntity(createAccountDto);
        verify(accountRepository).save(accountEntity);
        verify(accountMapper).toDto(accountPersisted);
    }

    @Test
    void update_whenAccountIsUpdated_shouldReturnAccountResponseDto() {
        var updateAccountDto = AccountDtoFixtures.updateAccountDtoOne();
        var accountPersisted = AccountEntityFixtures.adminAccountPersisted(1L);
        var accountUpdated = AccountEntityFixtures.adminAccountPersisted(1L);

        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(accountPersisted));
        when(accountRepository.save(accountPersisted))
                .thenReturn(accountUpdated);
        when(accountMapper.toDto(accountUpdated))
                .thenReturn(AccountDtoFixtures.adminAccountResponseDto(1L));

        var account = accountService.update(1L, updateAccountDto);

        assertEquals(1L, account.id());
        assertEquals("admin", account.username());
    }

    @Test
    void delete_whenAccountIsRemoved_shouldReturnNull() {
        InOrder inOrder = inOrder(accountRepository);

        var accountPersisted = AccountEntityFixtures.adminAccountPersisted(1L);

        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(accountPersisted));

        accountService.delete(1L);

        inOrder.verify(accountRepository).findById(1L);
        inOrder.verify(accountRepository, times(1)).delete(accountPersisted);
    }

    @Test
    void delete_whenAccountDoesNotExist_shouldThrowException() {
        when(accountRepository.findById(1L))
                .thenReturn(Optional.empty());

        //TODO: modify service to use custom exception
        var exception = assertThrows(RuntimeException.class, () -> accountService.delete(1L));

        assertEquals("Account not found", exception.getMessage());
        verify(accountRepository, never()).delete(any());
    }

}