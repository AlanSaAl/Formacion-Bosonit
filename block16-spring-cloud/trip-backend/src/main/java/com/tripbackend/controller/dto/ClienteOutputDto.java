package com.tripbackend.controller.dto;

import lombok.Data;

@Data
public class ClienteOutputDto {
    private int idCliente;

    private String nombre;

    private String apellido;

    private int edad;

    private String email;

    private String telefono;
}
