package com.bookamania.universe.api.event;

import com.bookamania.universe.domain.model.valueobject.UniverseId;

public record UniverseCreatedEvent(UniverseId universeId, String name) {
}
