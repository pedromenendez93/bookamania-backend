package com.pmenendez.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingTest {

    private Greeting greeting;

    @BeforeEach
    void setUp() {
        greeting = new Greeting("Hello world!");
    }


    @Test
    void givenAGreeting_whenGetContent_thenReturnContent() {
        assertEquals("Hello world!", greeting.content());
    }


}
