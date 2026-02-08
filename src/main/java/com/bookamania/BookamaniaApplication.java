package com.bookamania;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookamaniaApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BookamaniaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Bookamania is ready to cook!");
    }
}
