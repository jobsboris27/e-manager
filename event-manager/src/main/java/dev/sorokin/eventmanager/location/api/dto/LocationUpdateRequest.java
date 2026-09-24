package dev.sorokin.eventmanager.location.api.dto;

import jakarta.validation.constraints.*;

public record LocationUpdateRequest(
        @NotBlank(message = "Название обязательно")
        @Size(max = 255, message = "Название не должно превышать 255 символов")
        String name,

        @Size(max = 255, message = "Адрес не должен превышать 255 символов")
        String address,

        @NotNull(message = "Вместимость обязательна")
        @Positive(message = "Вместимость должна быть положительным числом")
        @Min(value = 5, message = "Вместимость должна быть больше или равна 5")
        Integer capacity
) {}