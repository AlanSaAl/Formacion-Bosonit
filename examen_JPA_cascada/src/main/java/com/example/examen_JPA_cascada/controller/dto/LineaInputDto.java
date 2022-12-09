package com.example.examen_JPA_cascada.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LineaInputDto {
    int idFra;

    String proNomb;

    double cantidad;

    double precio;
}
