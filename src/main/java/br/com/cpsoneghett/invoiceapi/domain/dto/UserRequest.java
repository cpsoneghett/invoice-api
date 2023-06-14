package br.com.cpsoneghett.invoiceapi.domain.dto;

import br.com.cpsoneghett.invoiceapi.domain.enums.UserType;
import br.com.cpsoneghett.invoiceapi.domain.model.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(@NotBlank String name, @NotBlank String email, @NotBlank String password,
                          @NotNull UserType type) {

    public UserEntity toEntity() {
        return new UserEntity(this.name, this.email, this.password, this.type);
    }
}
