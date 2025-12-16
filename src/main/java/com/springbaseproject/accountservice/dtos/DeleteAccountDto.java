package com.springbaseproject.accountservice.dtos;

import lombok.NonNull;

public record DeleteAccountDto(
        @NonNull Long id
) {
    @NonNull
    @Override
    public String toString() {
        return "DeleteAccountDto{" +
                "id=" + id +
                '}';
    }
}
