package com.bookamania.universe.application.port.out;

import com.bookamania.universe.domain.model.Universe;

public interface UniverseRepository {

    void save(Universe universe);
}
