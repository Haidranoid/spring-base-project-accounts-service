package com.springbaseproject.accountservice.data.repositories;

import com.springbaseproject.accountservice.fixtures.AccountFixtures;
import com.springbaseproject.accountservice.mappers.AccountMapper;
import com.springbaseproject.accountservice.mappers.impl.AccountMapperImpl;
import com.springbaseproject.accountservice.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

//@Transactional - DataJpaTest already is Transactional
@DataJpaTest
public class AccountRepositoryTest {

    AccountMapper accountMapper = new AccountMapperImpl();

    @Autowired
    AccountRepository accountRepository;

    @Test
    void save_shouldPersistOneAccount(){
        var createAdminAccountDtoFixture = AccountFixtures.createAdminAccountDto();

        accountRepository.save(accountMapper.toEntity(createAdminAccountDtoFixture));
        var accounts = accountRepository.findAll();

        assertThat(accounts).hasSize(1);
    }

    @Test
    void delete_shouldPersistAndRemoveOneAccount(){
        var createAdminAccountDtoFixture = AccountFixtures.createAdminAccountDto();

        var accountSaved = accountRepository.save(accountMapper.toEntity(createAdminAccountDtoFixture));
        var accounts = accountRepository.findAll();

        assertThat(accounts).hasSize(1);

        accountRepository.delete(accountSaved);

        accounts = accountRepository.findAll();

        assertThat(accounts).hasSize(0);
    }

    @Test
    void findById_shouldReturnAccount(){
        var createAdminAccountDtoFixture = AccountFixtures.createAdminAccountDto();

        var accountSaved = accountRepository.save(accountMapper.toEntity(createAdminAccountDtoFixture));
        var account = accountRepository.findById(accountSaved.getId());

        assertThat(account).isPresent();
    }

    @Test
    void findByUsername_shouldReturnAccount(){
        var createAdminAccountDtoFixture = AccountFixtures.createAdminAccountDto();

        accountRepository.save(accountMapper.toEntity(createAdminAccountDtoFixture));
        var account = accountRepository.findByUsername(createAdminAccountDtoFixture.username());

        assertThat(account).isPresent();
    }

    @Test
    void findByEmail_shouldReturnAccount(){
        var createAdminAccountDtoFixture = AccountFixtures.createAdminAccountDto();

        accountRepository.save(accountMapper.toEntity(createAdminAccountDtoFixture));
        var account = accountRepository.findByEmail(createAdminAccountDtoFixture.email());

        assertThat(account).isPresent();
    }

    @Test
    void findByAll_shouldReturnTwoAccounts(){
        var createAdminAccountDtoFixture = AccountFixtures.createAdminAccountDto();
        var createManagerAccountDtoFixture = AccountFixtures.createManagerAccountDto();

        accountRepository.save(accountMapper.toEntity(createAdminAccountDtoFixture));
        accountRepository.save(accountMapper.toEntity(createManagerAccountDtoFixture));

        var accounts = accountRepository.findAll();

        assertThat(accounts).hasSize(2);
    }
}
