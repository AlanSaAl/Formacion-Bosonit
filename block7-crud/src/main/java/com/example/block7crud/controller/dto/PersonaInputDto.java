package com.example.block7crud.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaInputDto {
    int id;
    @NotBlank(message = "Nombre obligatorio")
    String nombre;
    @NotBlank(message = "Edad obligatoria")
    String edad;
    @NotBlank(message = "Población obligatoria")
    String poblacion;
}
