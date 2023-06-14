package br.com.cpsoneghett.invoiceapi.controller;

import br.com.cpsoneghett.invoiceapi.config.security.annotations.CanReadUsers;
import br.com.cpsoneghett.invoiceapi.config.security.annotations.CanWriteUsers;
import br.com.cpsoneghett.invoiceapi.domain.dto.UserRequest;
import br.com.cpsoneghett.invoiceapi.domain.dto.UserResponse;
import br.com.cpsoneghett.invoiceapi.domain.dto.UserUpdateRequest;
import br.com.cpsoneghett.invoiceapi.domain.model.UserEntity;
import br.com.cpsoneghett.invoiceapi.domain.repository.UserRepository;
import br.com.cpsoneghett.invoiceapi.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @CanReadUsers
    public Page<UserResponse> findAll(Pageable pageable) {
        final Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
        final var userResponses = userEntityPage.getContent().stream().map(UserResponse::of).collect(Collectors.toList());
        return new PageImpl<>(userResponses, pageable, userEntityPage.getTotalElements());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CanWriteUsers
    public UserResponse create(@RequestBody @Valid UserRequest userRequest) {
        return UserResponse.of(userRepository.save(userRequest.toEntity()));
    }

    @GetMapping("/{id}")
    @CanReadUsers
    public UserResponse findById(@PathVariable UUID id) {
        return UserResponse.of(userRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CanWriteUsers
    public UserResponse update(@PathVariable UUID id, @RequestBody UserUpdateRequest request) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        request.update(user);
        userRepository.save(user);
        return UserResponse.of(user);
    }
}
