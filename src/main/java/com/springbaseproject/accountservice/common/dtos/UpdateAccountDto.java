package com.springbaseproject.accountservice.common.dtos;

import com.springbaseproject.sharedstarter.constants.Roles;
import lombok.Builder;
import lombok.NonNull;

//TODO: remove NonNull from update dtos
@Builder
public record UpdateAccountDto(
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String email,
        @NonNull Roles role
) {
    @NonNull
    @Override
    public String toString() {
        return "UpdateAccountDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
