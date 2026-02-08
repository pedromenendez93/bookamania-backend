package com.bookamania.universe.infrastructure.persistence.jdbc.show.repository;

import com.bookamania.universe.infrastructure.persistence.jdbc.show.entity.ShowJdbcEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ShowCrudRepository extends CrudRepository<ShowJdbcEntity, UUID> {

    List<ShowJdbcEntity> findAllByUniverseId(UUID universeId);
}
