package br.com.cpsoneghett.invoiceapi.config.api;

import br.com.cpsoneghett.invoiceapi.domain.enums.UserType;
import br.com.cpsoneghett.invoiceapi.domain.model.UserEntity;
import br.com.cpsoneghett.invoiceapi.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class FirstUserConfiguration implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(FirstUserConfiguration.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public FirstUserConfiguration(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() != 0) {
            return;
        }

        logger.info("NO USERS FOUND! Registering test users for the first time.");

        String encodedSamplePassword = passwordEncoder.encode("123456");

        userRepository.save(new UserEntity("Christiano Soneghett", "admin@email.com", encodedSamplePassword, UserType.ADMIN));

        userRepository.save(new UserEntity("Test Client Application", "client@email.com", encodedSamplePassword, UserType.CLIENT));
    }
}
