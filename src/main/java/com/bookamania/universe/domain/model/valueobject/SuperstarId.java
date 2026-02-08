package com.bookamania.universe.domain.model.valueobject;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class SuperstarId {

    private final UUID value;

    private SuperstarId(UUID value) {
        this.value = requireNonNull(value);
    }

    public static SuperstarId create() {
        return new SuperstarId(UUID.randomUUID());
    }

    public static SuperstarId of(UUID value) {
        return new SuperstarId(value);
    }

    private UUID getValue() {
        return value;
    }
}
