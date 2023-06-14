package br.com.cpsoneghett.invoiceapi.config.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties("cpson.auth")
public class AuthProperties {

    @NotNull
    private JksProperties jks;

    public JksProperties getJks() {
        return jks;
    }

    public void setJks(JksProperties jksProperties) {
        this.jks = jksProperties;
    }

    record JksProperties(@NotBlank String keypass, @NotBlank String storepass, @NotBlank String alias,
                         @NotBlank String path) {
    }
}
