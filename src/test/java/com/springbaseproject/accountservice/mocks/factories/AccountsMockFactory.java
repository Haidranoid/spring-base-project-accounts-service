package com.springbaseproject.accountservice.mocks.factories;

import com.springbaseproject.accountservice.common.dtos.AccountResponseDto;
import com.springbaseproject.sharedstarter.constants.Roles;
import com.springbaseproject.sharedstarter.entities.Account;

public class AccountsMockFactory {

        public static Account accountOne() {
                return Account.builder()
                        .id(1L)
                        .username("fake@email.com")
                        .email("fake@email.com")
                        .password("<PASSWORD>")
                        .firstName("Steve")
                        .lastName("Rogers")
                        .role(Roles.ADMIN)
                        .build();
        }

        public static Account accountTwo() {
                return Account.builder()
                        .id(2L)
                        .username("fake2@email.com")
                        .email("fake2@email.com")
                        .password("<PASSWORD>")
                        .firstName("Dooms")
                        .lastName("Days")
                        .role(Roles.ADMIN)
                        .build();
        }

        public static AccountResponseDto accountResponseDtoOne() {
                return AccountResponseDto.builder()
                        .id(1L)
                        .username("fake@email.com")
                        .email("fake@email.com")
                        .firstName("Steve")
                        .lastName("Rogers")
                        .role(Roles.ADMIN)
                        .build();
        }
}
