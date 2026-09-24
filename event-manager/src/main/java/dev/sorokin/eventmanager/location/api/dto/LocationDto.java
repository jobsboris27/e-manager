package dev.sorokin.eventmanager.location.api.dto;

public record LocationDto(
        Long id,
        String name,
        String address,
        Integer capacity
) {}