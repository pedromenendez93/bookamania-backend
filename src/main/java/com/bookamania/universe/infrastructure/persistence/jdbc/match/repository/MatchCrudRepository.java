package com.bookamania.universe.infrastructure.persistence.jdbc.match.repository;

import com.bookamania.universe.infrastructure.persistence.jdbc.match.entity.MatchJdbcEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface MatchCrudRepository extends CrudRepository<MatchJdbcEntity, UUID> {

    List<MatchJdbcEntity> findAllByShowId(UUID showId);
}
