package com.bookamania.universe.domain.model.valueobject;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class MatchId {

    private final UUID value;

    private MatchId(UUID value) {
        this.value = requireNonNull(value);
    }

    public static MatchId create() {
        return new MatchId(UUID.randomUUID());
    }

    public static MatchId of(UUID value) {
        return new MatchId(value);
    }

    public UUID getValue() {
        return value;
    }
}
