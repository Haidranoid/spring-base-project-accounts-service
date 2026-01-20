package com.springbaseproject.accountservice.integration.repositories;

import com.springbaseproject.accountservice.fixtures.AccountEntityFixtures;
import com.springbaseproject.accountservice.integration.base.AbstractPostgresIT;
import com.springbaseproject.accountservice.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryIT extends AbstractPostgresIT {

    @Autowired
    AccountRepository accountRepository;

    //@Test
    void findAccountByEmail_shouldReturnAccount(){
        var adminAccountFixture = AccountEntityFixtures.adminAccount();
        accountRepository.save(adminAccountFixture);

        var accountFound = accountRepository.findByEmail(adminAccountFixture.getEmail());

        assertThat(accountFound).isPresent();
    }

    //@Test
    void save_shouldPersistEntity(){
        var account = AccountEntityFixtures.adminAccount();
        accountRepository.save(account);

        var accounts = accountRepository.findAll();

        assertThat(accounts).hasSize(1);
    }

}
