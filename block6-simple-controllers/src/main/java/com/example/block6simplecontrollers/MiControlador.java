package com.example.block6simplecontrollers;

import org.springframework.web.bind.annotation.*;

@RestController
public class MiControlador {
    @GetMapping("/user/{nombre}")
    public String hola(@PathVariable String nombre) {
        return "Hola " + nombre;
    }
    @PostMapping("/useradd")
    public Persona usuario(@RequestBody Persona persona){
        persona.setEdad(persona.getEdad() + 1);
        return persona;
    }
}
