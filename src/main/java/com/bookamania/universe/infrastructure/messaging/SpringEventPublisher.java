package com.bookamania.universe.infrastructure.messaging;

import com.bookamania.universe.application.port.out.EventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SpringEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher delegate;

    @Override
    public void publish(Object event) {
        delegate.publishEvent(event);
    }
}
