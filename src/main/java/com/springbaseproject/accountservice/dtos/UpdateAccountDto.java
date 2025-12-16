package com.springbaseproject.accountservice.dtos;

import com.springbaseproject.sharedstarter.constants.Roles;
import lombok.NonNull;

public record UpdateAccountDto(
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String email,
        @NonNull String password,
        @NonNull Roles role
) {
    @NonNull
    @Override
    public String toString() {
        return "UpdateAccountDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "[password]" + '\'' +
                ", role=" + role +
                '}';
    }
}
