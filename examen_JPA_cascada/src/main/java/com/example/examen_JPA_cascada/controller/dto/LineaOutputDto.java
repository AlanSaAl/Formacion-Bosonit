package com.example.examen_JPA_cascada.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LineaOutputDto {
    int idFra;

    String ProNomb;

    double cantidad;

    double precio;
}
