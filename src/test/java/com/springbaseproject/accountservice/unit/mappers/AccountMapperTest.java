package com.springbaseproject.accountservice.unit.mappers;

import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.accountservice.mappers.AccountMapper;
import com.springbaseproject.accountservice.mappers.impl.AccountMapperImpl;
import com.springbaseproject.sharedstarter.constants.Roles;
import com.springbaseproject.sharedstarter.entities.Account;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountMapperTest {

    private final AccountMapper mapper = new AccountMapperImpl();

    @Test
    void toDto_shouldMapAllFields() {
        Account account =  Account.builder()
                .id(1L)
                .username("user@test.com")
                .email("user@test.com")
                .firstName("Steve")
                .lastName("Rogers")
                .password("pwd")
                .role(Roles.USER)
                .build();

        AccountResponseDto dto = mapper.toDto(account);

        assertThat(dto.email()).isEqualTo("user@test.com");
        assertThat(dto.firstName()).isEqualTo("Steve");
        assertThat(dto.lastName()).isEqualTo("Rogers");
    }
}
