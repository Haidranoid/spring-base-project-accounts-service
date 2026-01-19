package com.springbaseproject.accountservice.factories;

import com.springbaseproject.sharedstarter.constants.Roles;
import com.springbaseproject.sharedstarter.entities.AccountEntity;
import net.datafaker.Faker;

public class AccountTestDataFactory {

    private static final Faker faker = new Faker();

    public static AccountEntity randomAccount() {
        return AccountEntity.builder()
                .id(faker.number().randomNumber())
                .username(faker.credentials().username())
                .email(faker.internet().emailAddress())
                .password(faker.credentials().password())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .role(Roles.USER)
                .build();
    }
}
