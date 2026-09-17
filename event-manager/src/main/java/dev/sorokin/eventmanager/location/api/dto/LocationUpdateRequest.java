package dev.sorokin.eventmanager.location.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LocationUpdateRequest(
        @NotBlank(message = "Название обязательно")
        @Size(max = 255, message = "Название не должно превышать 255 символов")
        String name,

        @Size(max = 255, message = "Адрес не должен превышать 255 символов")
        String address,

        @NotNull(message = "Вместимость обязательна")
        @Positive(message = "Вместимость должна быть положительным числом")
        Integer capacity
) {}