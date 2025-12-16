package com.springbaseproject.accountservice.dtos;

import com.springbaseproject.sharedstarter.constants.Roles;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record CreateAccountDto(
        @NonNull String username,
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String email,
        @NonNull String password,
        @NonNull Roles role
) {
    @NonNull
    @Override
    public String toString() {
        return "CreateUserDto{" +
                "role=" + role +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + "[password]" + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
