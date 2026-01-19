package com.springbaseproject.accountservice.common.dtos;

import com.springbaseproject.sharedstarter.constants.Roles;
import lombok.Builder;
import lombok.NonNull;

@Builder
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
