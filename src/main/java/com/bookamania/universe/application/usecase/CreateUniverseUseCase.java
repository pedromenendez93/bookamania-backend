package com.bookamania.universe.application.usecase;

import com.bookamania.universe.application.command.CreateUniverseCommand;

public interface CreateUniverseUseCase {

    void execute(CreateUniverseCommand command);

}
