package com.bookamania.universe.infrastructure.persistence.jdbc.show.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "show")
@Value
public class ShowJdbcEntity {

    @Id
    UUID id;
    UUID universeId;
    LocalDate date;

}
