package dev.sorokin.eventmanager.location.model;

import lombok.Getter;

@Getter
public class Location {

    private final Long id;
    private final String name;
    private final String address;
    private final int capacity;

    public Location(Long id, String name, String address, int capacity) {
        validateCapacity(capacity);
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    private void validateCapacity(int capacity) {
        if (capacity <= 5) {
            throw new IllegalArgumentException("Вместимость должна быть не менее 5");
        }
    }

    public boolean canAccommodate(int peopleCount) {
        return peopleCount <= this.capacity;
    }
}
