package dev.sorokin.eventmanager.location.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LocationCreateRequest(
    @NotBlank(message = "Название обязательно")
    String name,
    String address,
    @NotNull(message = "Вместимость обязательна")
    @Positive(message = "Вместимость должна быть положительной")
    @Min(value = 5, message = "Вместимость должна быть больше или равна 5")
    Integer capacity
) {}