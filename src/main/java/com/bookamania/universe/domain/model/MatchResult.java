package com.bookamania.universe.domain.model;

import com.bookamania.universe.domain.model.valueobject.SuperstarId;

import java.util.Objects;

public class MatchResult {

    private final SuperstarId winner;

    public MatchResult(SuperstarId winner) {
        this.winner = Objects.requireNonNull(winner);
    }

    public SuperstarId winner() {
        return winner;
    }
}
