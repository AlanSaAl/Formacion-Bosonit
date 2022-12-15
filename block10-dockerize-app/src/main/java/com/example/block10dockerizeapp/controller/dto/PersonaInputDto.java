package com.example.block10dockerizeapp.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PersonaInputDto {
    String nombre;

    String apellido;

    Date fecha;
}
