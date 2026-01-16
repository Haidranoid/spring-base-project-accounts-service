package com.springbaseproject.accountservice.services.impl;

import com.springbaseproject.accountservice.common.dtos.*;
import com.springbaseproject.accountservice.mappers.impl.AccountMapperImpl;
import com.springbaseproject.accountservice.repositories.AccountRepository;
import com.springbaseproject.accountservice.services.AccountService;
import com.springbaseproject.sharedstarter.entities.Account;
import com.springbaseproject.sharedstarter.utils.SecurityUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapperImpl accountMapper;
    private final SecurityUtils securityUtils;

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponseDto> findAll() {
        var accountsList = accountRepository.findAll().stream().map(accountMapper::toDto).toList();

        return accountsList;
    }

    @Override
    public AccountResponseDto findById(Long userId) {
        var account = accountRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found"));

        return accountMapper.toDto(account);
    }

    @Override
    public AccountResponseDto create(CreateAccountDto createAccountDto) {
        var accountEntity = accountMapper.toEntity(createAccountDto);
        var accountSaved = accountRepository.save(accountEntity);

        return accountMapper.toDto(accountSaved);
    }

    @Override
    public AccountResponseDto update(Long userId, UpdateAccountDto updateAccountDto) {
        var account = accountRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found"));

        //applyUpdates(accountDB, accountDto);

        var accountUpdated = accountRepository.save(account);

        return accountMapper.toDto(accountUpdated);
    }

    @Override
    public void delete(Long userId) {
        var account = accountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        accountRepository.delete(account);
    }


    public AccountResponseDto authenticateAccount(AuthenticateAccountDto authenticateAccountDto) {
        var account = accountRepository.findByUsername(authenticateAccountDto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return accountMapper.toDto(account);
    }

    private void applyUpdates(Account entity, UpdateAccountDto dto) {
        if (!dto.password().isBlank()) {
            entity.setPassword(passwordEncoder.encode(dto.password()));
        }

        entity.setRole(dto.role());
    }

    public void changePassword(ChangePasswordAccountDto changePasswordAccountDto) {
        var account = securityUtils.getCurrentAccount();

        // check if the current password is matches, otherwise throws error
        if (!passwordEncoder.matches(changePasswordAccountDto.currentPassword(), account.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }

        // check if the new password and the confirmation password are the same,
        if (!changePasswordAccountDto.newPassword().equals(changePasswordAccountDto.confirmationPassword())) {
            throw new IllegalStateException("Passwords are not the same");
        }

        // update the password
        account.setPassword(passwordEncoder.encode(changePasswordAccountDto.newPassword()));

        // save the new password
        accountRepository.save(account);
    }

}
