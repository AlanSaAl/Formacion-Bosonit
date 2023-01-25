package com.tripfrontend.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ViajeOutputDto {
    private int idViaje;

    private String origen;

    private String destino;

    private LocalDate fechaPartida;

    private LocalDate fechaLlegada;

    private String pasajero;

    private String estatus;
}
