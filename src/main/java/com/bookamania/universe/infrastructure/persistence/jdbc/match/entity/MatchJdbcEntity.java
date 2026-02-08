package com.bookamania.universe.infrastructure.persistence.jdbc.match.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Value;

import java.util.UUID;

@Table(name = "match")
@Value
public class MatchJdbcEntity {

    @Id
    UUID id;
    UUID showId;
    int matchOrder;
}
