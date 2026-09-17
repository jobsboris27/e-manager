package dev.sorokin.eventmanager.location.api;

import dev.sorokin.eventmanager.location.api.dto.LocationCreateRequest;
import dev.sorokin.eventmanager.location.api.dto.LocationDto;
import dev.sorokin.eventmanager.location.api.dto.LocationUpdateRequest;
import dev.sorokin.eventmanager.location.mapper.LocationMapper;
import dev.sorokin.eventmanager.location.model.Location;
import dev.sorokin.eventmanager.location.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService service;
    private final LocationMapper mapper;

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAll() {
        List<Location> domains = service.getAll();
        return ResponseEntity.ok(mapper.toDtoList(domains));
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDto> getById(@PathVariable Long locationId) {
        Location domain = service.get(locationId);
        return ResponseEntity.ok(mapper.toDto(domain));
    }

    @PostMapping
    public ResponseEntity<LocationDto> create(@Valid @RequestBody LocationCreateRequest request) {
        Location created = service.create(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(created));
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<LocationDto> update(
            @PathVariable Long locationId,
            @Valid @RequestBody LocationUpdateRequest request
    ) {
        Location updated = service.update(locationId, mapper.toDomain(request));
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> delete(@PathVariable Long locationId) {
        service.delete(locationId);
        return ResponseEntity.noContent().build();
    }
}