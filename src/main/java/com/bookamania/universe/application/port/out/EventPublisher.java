package com.bookamania.universe.application.port.out;

public interface EventPublisher {

    void publish(Object event);
}
