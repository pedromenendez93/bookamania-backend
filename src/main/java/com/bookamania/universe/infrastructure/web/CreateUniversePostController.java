package com.bookamania.universe.infrastructure.web;

import com.bookamania.universe.application.command.CreateUniverseCommand;
import com.bookamania.universe.application.usecase.CreateUniverseUseCase;
import com.bookamania.universe.infrastructure.web.dto.CreateUniverseRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/universe")
@AllArgsConstructor
public class CreateUniversePostController {

    private final CreateUniverseUseCase createUniverseUseCase;

    @PostMapping
    public void createUniverse(@RequestBody CreateUniverseRequest request) {
        createUniverseUseCase.execute(new CreateUniverseCommand(request.name()));
    }
}
