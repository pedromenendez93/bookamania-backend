package com.bookamania.universe.domain.model.valueobject;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class UniverseId {

    private final UUID value;

    private UniverseId(UUID value) {
        this.value = requireNonNull(value);
    }

    public static UniverseId create() {
        return new UniverseId(UUID.randomUUID());
    }

    public static UniverseId of(UUID value) {
        return new UniverseId(value);
    }

    private UUID getValue() {
        return value;
    }

}
