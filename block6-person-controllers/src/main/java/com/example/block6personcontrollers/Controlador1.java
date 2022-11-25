package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("controlador1")
public class Controlador1 {
    @Autowired
    Servicio servicio;

    @GetMapping("addPersona")
    public Persona addPersona(@RequestHeader("nombre") String nombre,
                              @RequestHeader("poblacion") String poblacion,
                              @RequestHeader("edad") int edad){
        return servicio.crearPersona(nombre, poblacion, edad);
    }
    @PostMapping("addCiudad")
    public void addCiudad(@RequestBody Ciudad ciudad){
        servicio.addCiudad(ciudad);
    }
}
