package com.bookamania.universe.infrastructure.persistence.jdbc.universe.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Table(name = "universe")
public class UniverseJdbcEntity {

    @Id
    private final UUID id;
    private final String name;

    public UniverseJdbcEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}