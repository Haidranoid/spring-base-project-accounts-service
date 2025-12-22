package com.springbaseproject.accountservice.common.dtos;

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
