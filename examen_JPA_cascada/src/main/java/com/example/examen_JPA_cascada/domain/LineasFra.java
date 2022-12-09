package com.example.examen_JPA_cascada.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class LineasFra {
    @Id
    @GeneratedValue
    int idLinea;

    @ManyToOne
    @JoinColumn(name = "idFra")
    CabeceraFra cabeceraFra;

    @Column(name = "pro_nomb", nullable = false)
    String proNomb;

    double cantidad;

    double precio;
}
