package br.com.cpsoneghett.invoiceapi.domain.dto;

import br.com.cpsoneghett.invoiceapi.domain.enums.UserType;
import br.com.cpsoneghett.invoiceapi.domain.model.UserEntity;

import java.util.UUID;

public record UserResponse(UUID id, String name, String email, UserType type) {

    public static UserResponse of(UserEntity user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getName(), user.getType());
    }

}
