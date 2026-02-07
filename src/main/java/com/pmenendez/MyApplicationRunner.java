package com.pmenendez;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplicationRunner implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyApplicationRunner.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Spring boot java archetype example");
    }
}
