package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClaseTres implements CommandLineRunner {
    @Value("${project.saludo}")
    private String saludo;

    @Override
    public void run(String... args) throws Exception{
        System.out.println(saludo);
    }
}
