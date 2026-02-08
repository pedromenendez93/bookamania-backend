package com.bookamania.universe.domain.model.valueobject;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class ShowId {

    private final UUID value;

    private ShowId(UUID value) {
        this.value = requireNonNull(value);
    }

    public static ShowId create() {
        return new ShowId(UUID.randomUUID());
    }

    public static ShowId of(UUID value) {
        return new ShowId(value);
    }

    public UUID getValue() {
        return value;
    }
}
