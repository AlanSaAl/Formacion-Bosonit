package com.example.block6personcontrollers;

import java.util.List;

public interface IServicio {
    Persona crearPersona(String nombre, String poblacion, int edad);
    Persona getPersonaConEdadMul();
    void addCiudad(Ciudad ciudad);
    List<Ciudad> getListaCiudades();
}
