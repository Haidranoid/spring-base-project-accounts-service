package com.springbaseproject.accountservice.dtos;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record ChangePasswordAccountDto(
        @NonNull String currentPassword,
        @NonNull String newPassword,
        @NonNull String confirmationPassword
) {
    @NonNull
    @Override
    public String toString() {
        return "ChangePasswordAccountDto{" +
                "currentPassword='" + "[currentPassword]" + '\'' +
                ", newPassword='" + "[newPassword]" + '\'' +
                ", confirmationPassword='" + "[confirmationPassword]" + '\'' +
                '}';
    }
}
