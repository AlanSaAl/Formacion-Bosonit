package org.example;

import java.util.Optional;

public class Persona {
    private String nombre;
    private Optional<Integer> edad;
    private String ciudad;

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }

    public Persona(String nombre, Integer edad, String ciudad) {
        this.nombre = nombre;
        this.edad = Optional.of(edad);
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Optional<Integer> getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = Optional.of(edad);
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
