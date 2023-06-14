package br.com.cpsoneghett.invoiceapi.domain.dto;

import br.com.cpsoneghett.invoiceapi.domain.enums.UserType;
import br.com.cpsoneghett.invoiceapi.domain.model.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

public record UserUpdateRequest(@NotBlank String name, @NotBlank String email, @NotNull UserType type) {

    public void update(UserEntity currentUser) {
        BeanUtils.copyProperties(this, currentUser);
    }
}
