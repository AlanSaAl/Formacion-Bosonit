package com.example.examen_JPA_cascada.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
public class Cliente {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String nombre;
}
