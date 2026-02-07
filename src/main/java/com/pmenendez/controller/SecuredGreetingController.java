package com.pmenendez.controller;

import com.pmenendez.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secured/greeting")
public class SecuredGreetingController {

    @GetMapping
    public Greeting securedGreeting() {
        return new Greeting("Hello secured World");
    }
}
