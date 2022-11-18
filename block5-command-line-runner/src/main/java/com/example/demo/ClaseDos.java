package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClaseDos {
    @Bean
    CommandLineRunner ejecutame()
    {
        return p ->
        {
            System.out.println("Hola desde clase secundaria");
        };
    }
}
