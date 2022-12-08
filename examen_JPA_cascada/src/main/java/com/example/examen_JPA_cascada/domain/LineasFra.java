package com.example.examen_JPA_cascada.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
// Clase para la tabla las lineas de la factura
public class LineasFra {
    // id de la linea
    @Id
    @GeneratedValue
    int idLinea;

    // id de la factura
    int idFra;

    // nombre del producto
    @Column(name = "pro_nomb", nullable = false)
    String ProNomb;

    double cantidad;

    double precio;
}
