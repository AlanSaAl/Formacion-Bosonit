package com.tripbackend.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class ViajeInputDto {
    private int idViaje;

    private String origen;

    private String destino;

    private LocalDate fechaPartida;

    private LocalDate fechaLlegada;

    private String estatus;
}
