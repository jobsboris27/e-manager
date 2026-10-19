package dev.sorokin.eventmanager.user.api.dto;

import jakarta.validation.constraints.NotBlank;

public record SignInRequest(
        @NotBlank String login,
        @NotBlank String password
) {}