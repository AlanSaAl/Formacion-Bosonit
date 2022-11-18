package com.example.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ClaseUno {
    @PostConstruct
    public void primer_saludo(){
        System.out.println("Hola desde clase inicial");
    }
}
