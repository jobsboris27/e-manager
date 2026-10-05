package dev.sorokin.eventmanager.user.mapper;

import dev.sorokin.eventmanager.user.api.dto.UserResponse;
import dev.sorokin.eventmanager.user.entity.UserEntity;
import dev.sorokin.eventmanager.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toDomain(UserEntity entity) {
        return new User(entity.getId(), entity.getLogin(),
                entity.getPasswordHash(), entity.getRole());
    }

    public UserEntity toEntity(User domain) {
        return new UserEntity(domain.id(), domain.login(),
                domain.passwordHash(), domain.role());
    }

    public UserResponse toDto(User domain) {
        return new UserResponse(domain.id(), domain.login(), domain.role());
    }
}