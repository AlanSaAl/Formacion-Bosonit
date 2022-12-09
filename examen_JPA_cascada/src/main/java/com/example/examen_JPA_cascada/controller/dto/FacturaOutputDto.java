package com.example.examen_JPA_cascada.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class FacturaOutputDto {
    int id;

    double importeFra = 0;

    @JsonProperty
    ClienteOutputDto clienteOutput;

    List<LineaOutputDto> lineaOutputDto;
}
