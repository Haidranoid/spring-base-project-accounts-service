package com.springbaseproject.accountservice.data.repositories;

import com.springbaseproject.accountservice.fixtures.AccountFixtures;
import com.springbaseproject.accountservice.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@Transactional - DataJpaTest already is Transactional
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void findAccountByEmail_shouldReturnAccount(){
        var adminAccountFixture = AccountFixtures.adminAccount();
        accountRepository.save(adminAccountFixture);

        var accountFound = accountRepository.findByEmail(adminAccountFixture.getEmail());

        assertThat(accountFound).isPresent();
    }

    @Test
    void save_shouldPersistEntity(){
        var account = AccountFixtures.adminAccount();
        accountRepository.save(account);

        var accounts = accountRepository.findAll();

        assertThat(accounts).hasSize(1);
    }

}
