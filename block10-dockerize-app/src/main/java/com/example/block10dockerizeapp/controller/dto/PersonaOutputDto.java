package com.example.block10dockerizeapp.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PersonaOutputDto {
    String nombre;

    String apellido;

    Date fecha;
}
