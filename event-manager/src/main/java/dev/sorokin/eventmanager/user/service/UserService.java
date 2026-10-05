package dev.sorokin.eventmanager.user.service;

import dev.sorokin.eventmanager.core.exception.EntityNotFoundException;
import dev.sorokin.eventmanager.user.api.dto.SignUpRequest;

import dev.sorokin.eventmanager.user.entity.UserRole;
import dev.sorokin.eventmanager.user.mapper.UserMapper;
import dev.sorokin.eventmanager.user.model.User;
import dev.sorokin.eventmanager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public User register(SignUpRequest request) {
        return create(new User(null, request.login(),
                passwordEncoder.encode(request.password()), UserRole.USER));
    }

    public void createAdmin(String login, String passwordHash) {
        create(new User(null, login, passwordHash, UserRole.ADMIN));
    }

    public boolean existsByLogin(String login) {
        return repository.existsByLogin(login);
    }

    public User getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User not found by id=" + id));
    }

    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login).map(mapper::toDomain);
    }

    private User create(User user) {
        if (repository.existsByLogin(user.login())) {
            throw new IllegalArgumentException(
                    "Login already taken: " + user.login());
        }
        return mapper.toDomain(repository.save(mapper.toEntity(user)));
    }
}
