package dev.sorokin.eventmanager.user.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @NotBlank @Size(min = 4) String login,
        @NotBlank @Size(min = 4) String password
) {}