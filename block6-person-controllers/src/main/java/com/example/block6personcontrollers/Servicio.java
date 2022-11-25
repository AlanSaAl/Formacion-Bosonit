package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Servicio implements IServicio{
    private final Persona persona = new Persona();
    //private final List<Ciudad> listaCiudades = new ArrayList<>();
    @Autowired
    List<Ciudad> listaCiudades;

    @Override
    public Persona crearPersona(String nombre, String poblacion, int edad){
        persona.setNombre(nombre);
        persona.setPoblacion(poblacion);
        persona.setEdad(edad);
        return persona;
    }
    @Override
    public Persona getPersonaConEdadMul(){
        int edadMultiplicada = persona.getEdad() * 2;
        persona.setEdad(edadMultiplicada);
        return persona;
    }
    @Override
    public void addCiudad(Ciudad ciudad){
        listaCiudades.add(ciudad);
    }
    @Override
    public List<Ciudad> getListaCiudades(){
        return listaCiudades;
    }
}