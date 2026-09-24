package dev.sorokin.eventmanager.location.service;

import dev.sorokin.eventmanager.core.exception.EntityNotFoundException;
import dev.sorokin.eventmanager.location.api.dto.LocationCreateRequest;
import dev.sorokin.eventmanager.location.api.dto.LocationDto;
import dev.sorokin.eventmanager.location.api.dto.LocationUpdateRequest;
import dev.sorokin.eventmanager.location.entity.LocationEntity;
import dev.sorokin.eventmanager.location.mapper.LocationMapper;
import dev.sorokin.eventmanager.location.model.Location;
import dev.sorokin.eventmanager.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository repository;
    private final LocationMapper mapper;

    public Location get(Long id) {
        return mapper.toDomain(findEntity(id));
    }

    public List<Location> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    public Location create(Location domain) {
        LocationEntity saved = repository.save(mapper.toEntity(domain));
        return mapper.toDomain(saved);
    }

    public Location update(Long id, Location domain) {
        findEntity(id);
        Location toSave = new Location(id, domain.getName(), domain.getAddress(), domain.getCapacity());
        LocationEntity saved = repository.save(mapper.toEntity(toSave));
        return mapper.toDomain(saved);
    }

    public void delete(Long id) {
        repository.delete(findEntity(id));
    }

    private LocationEntity findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location не найден: id=" + id));
    }
}