package com.tripbackend.controller.dto;

import com.tripbackend.domain.Cliente;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ViajeOutputDto {
    private int idViaje;

    private String origen;

    private String destino;

    private LocalDate fechaPartida;

    private LocalDate fechaLlegada;

    private List<Cliente> pasajeros = new ArrayList<>();

    private String estatus;
}
