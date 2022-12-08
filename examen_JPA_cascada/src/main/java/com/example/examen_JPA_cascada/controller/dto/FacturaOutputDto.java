package com.example.examen_JPA_cascada.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaOutputDto {
    int id;

    double importeFra = 0;

    ClienteOutputDto clienteOutput;

    List<LineaOutputDto> lineaOutputDto;
}
