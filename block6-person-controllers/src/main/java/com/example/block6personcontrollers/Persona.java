package com.example.block6personcontrollers;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Persona {
    private String nombre;
    private String poblacion;
    private int edad;
}
