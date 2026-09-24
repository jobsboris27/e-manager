package dev.sorokin.eventmanager.location.mapper;

import dev.sorokin.eventmanager.location.api.dto.LocationCreateRequest;
import dev.sorokin.eventmanager.location.api.dto.LocationDto;
import dev.sorokin.eventmanager.location.api.dto.LocationUpdateRequest;
import dev.sorokin.eventmanager.location.entity.LocationEntity;
import dev.sorokin.eventmanager.location.model.Location;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationMapper {
    public Location toDomain(LocationEntity entity) {
        return new Location(entity.getId(), entity.getName(), entity.getAddress(), entity.getCapacity());
    }

    public LocationEntity toEntity(Location domain) {
        return new LocationEntity(domain.getId(), domain.getName(), domain.getAddress(), domain.getCapacity());
    }

    public LocationDto toDto(Location domain) {
        return new LocationDto(domain.getId(), domain.getName(), domain.getAddress(), domain.getCapacity());
    }

    public Location toDomain(LocationCreateRequest request) {
        return new Location(null, request.name(), request.address(), request.capacity());
    }

    public Location toDomain(LocationUpdateRequest request) {
        return new Location(null, request.name(), request.address(), request.capacity());
    }

    public Location toDomain(Long id, LocationUpdateRequest request) {
        return new Location(id, request.name(), request.address(), request.capacity());
    }

    public List<LocationDto> toDtoList(List<Location> domains) {
        return domains.stream().map(this::toDto).toList();
    }
}