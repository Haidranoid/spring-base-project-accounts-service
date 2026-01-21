package com.springbaseproject.accountservice.unit.mappers;

import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.accountservice.common.dtos.CreateAccountDto;
import com.springbaseproject.accountservice.fixtures.AccountDtoFixtures;
import com.springbaseproject.accountservice.fixtures.AccountEntityFixtures;
import com.springbaseproject.accountservice.mappers.AccountMapper;
import com.springbaseproject.accountservice.mappers.impl.AccountMapperImpl;
import com.springbaseproject.sharedstarter.constants.Roles;
import com.springbaseproject.sharedstarter.entities.AccountEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountMapperTest {

    private final AccountMapper mapper = new AccountMapperImpl();

    @Test
    void toEntity_shouldMapAllFields() {
        CreateAccountDto createAccountDto = AccountDtoFixtures.createAdminAccountDto();

        AccountEntity accountEntity = mapper.toEntity(createAccountDto);

        assertThat(accountEntity.getUsername()).isEqualTo("admin");
        assertThat(accountEntity.getFirstName()).isEqualTo("Steve");
        assertThat(accountEntity.getLastName()).isEqualTo("Rogers");
        assertThat(accountEntity.getEmail()).isEqualTo("admin@email.com");
        assertThat(accountEntity.getPassword()).isEqualTo("<PASSWORD>");
        assertThat(accountEntity.getRole()).isEqualTo(Roles.ADMIN);
    }

    @Test
    void toDto_shouldMapAllFields() {
        AccountEntity accountEntity = AccountEntityFixtures.adminAccountPersisted(1L);

        AccountResponseDto dto = mapper.toDto(accountEntity);

        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.username()).isEqualTo("admin");
        assertThat(dto.firstName()).isEqualTo("Steve");
        assertThat(dto.lastName()).isEqualTo("Rogers");
        assertThat(dto.email()).isEqualTo("admin@email.com");
        assertThat(dto.role()).isEqualTo(Roles.ADMIN);
    }
}
