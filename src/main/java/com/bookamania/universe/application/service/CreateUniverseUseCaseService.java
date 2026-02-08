package com.bookamania.universe.application.service;

import com.bookamania.universe.api.event.UniverseCreatedEvent;
import com.bookamania.universe.application.command.CreateUniverseCommand;
import com.bookamania.universe.application.port.out.EventPublisher;
import com.bookamania.universe.application.port.out.UniverseRepository;
import com.bookamania.universe.application.usecase.CreateUniverseUseCase;
import com.bookamania.universe.domain.model.Universe;
import com.bookamania.universe.domain.model.valueobject.UniverseId;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUniverseUseCaseService implements CreateUniverseUseCase {

    private final UniverseRepository universeRepository;
    private final EventPublisher publisher;

    @Override
    public void execute(CreateUniverseCommand command) {
        UniverseId universeId = UniverseId.create();
        String universeName = command.name();

        Universe universe = new Universe(universeId, universeName);
        universeRepository.save(universe);

        publisher.publish(new UniverseCreatedEvent(universeId, universeName));
    }
}
