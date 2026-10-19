package dev.sorokin.eventmanager.user.api.dto;

import dev.sorokin.eventmanager.user.entity.UserRole;

public record UserResponse(Long id, String login, UserRole role) {}