package dev.sorokin.eventmanager.user.model;


import dev.sorokin.eventmanager.user.entity.UserRole;

public record User(
        Long id,
        String login,
        String passwordHash,
        UserRole role
) {}
