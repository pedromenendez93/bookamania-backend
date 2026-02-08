package com.bookamania.universe.infrastructure.persistence.jdbc.universe.repository;

import com.bookamania.universe.infrastructure.persistence.jdbc.universe.entity.UniverseJdbcEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UniverseCrudRepository extends CrudRepository<UniverseJdbcEntity, UUID> {
}
