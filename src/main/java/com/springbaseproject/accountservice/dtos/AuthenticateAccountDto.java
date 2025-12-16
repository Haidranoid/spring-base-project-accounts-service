package com.springbaseproject.accountservice.dtos;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record AuthenticateAccountDto(
       @NonNull String username,
       @NonNull String password
) {
    @NonNull
    @Override
    public String toString() {
        return "AuthenticateAccountDto{" +
                "username='" + username + '\'' +
                ", password='" + "[password]" + '\'' +
                '}';
    }
}
