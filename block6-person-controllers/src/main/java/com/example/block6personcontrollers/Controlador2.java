package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("controlador2")
public class Controlador2 {
    @Autowired
    Servicio servicio;

    @GetMapping("getPersona")
    public Persona getPersona(){
        return servicio.getPersonaConEdadMul();
    }
    @GetMapping("getCiudad")
    public List<Ciudad> getCiudad(){
        return servicio.getListaCiudades();
    }
}
