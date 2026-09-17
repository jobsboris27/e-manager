package dev.sorokin.eventmanager.location.repository;

import dev.sorokin.eventmanager.location.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
}
