package com.example.examen_JPA_cascada.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
// Clase para la tabla de cliente
public class Cliente {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String nombre;

    // Un cliente tiene muchas facturas
    /*@OneToMany(mappedBy = "cliente")
    List<CabeceraFra> factura = new ArrayList<>();*/
}
