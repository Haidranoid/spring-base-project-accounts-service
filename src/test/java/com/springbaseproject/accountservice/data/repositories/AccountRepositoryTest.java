package com.springbaseproject.accountservice.data.repositories;

import com.springbaseproject.accountservice.fixtures.AccountEntityFixtures;
import com.springbaseproject.accountservice.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

//@Transactional - DataJpaTest already is Transactional
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void save_shouldPersistOneAccount() {
        var accountEntity = AccountEntityFixtures.adminAccount();

        accountRepository.save(accountEntity);
        var accounts = accountRepository.findAll();

        assertThat(accounts).hasSize(1);
    }

    @Test
    void delete_shouldPersistAndRemoveOneAccount() {
        var accountEntity = AccountEntityFixtures.adminAccount();

        var accountSaved = accountRepository.save(accountEntity);
        var accounts = accountRepository.findAll();

        assertThat(accounts).hasSize(1);

        accountRepository.delete(accountSaved);

        accounts = accountRepository.findAll();

        assertThat(accounts).hasSize(0);
    }

    @Test
    void findById_shouldReturnAccount() {
        var accountEntity = AccountEntityFixtures.adminAccount();

        var accountSaved = accountRepository.save(accountEntity);
        var account = accountRepository.findById(accountSaved.getId());

        assertThat(account).isPresent();
    }

    @Test
    void findByUsername_shouldReturnAccount() {
        var accountEntity = AccountEntityFixtures.adminAccount();

        accountRepository.save(accountEntity);
        var account = accountRepository.findByUsername(accountEntity.getUsername());

        assertThat(account).isPresent();
    }

    @Test
    void findByEmail_shouldReturnAccount() {
        var accountEntity = AccountEntityFixtures.adminAccount();

        accountRepository.save(accountEntity);
        var account = accountRepository.findByEmail(accountEntity.getEmail());

        assertThat(account).isPresent();
    }

    @Test
    void findByAll_shouldReturnTwoAccounts() {
        var accountEntity = AccountEntityFixtures.adminAccount();
        var managerAccountFixture = AccountEntityFixtures.managerAccount();

        accountRepository.save(accountEntity);
        accountRepository.save(managerAccountFixture);

        var accounts = accountRepository.findAll();

        assertThat(accounts).hasSize(2);
    }
}
