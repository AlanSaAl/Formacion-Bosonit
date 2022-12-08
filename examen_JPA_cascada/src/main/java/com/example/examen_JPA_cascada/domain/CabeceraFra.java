package com.example.examen_JPA_cascada.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
// Clase para la tabla cabeceras de las facturas
public class CabeceraFra {
    @Id
    @GeneratedValue
    int id;

    // Codigo de cliente
    @Column(name = "cli_codi")
    int cliCodi;

    // Importe total de la factura
    @Column(name = "importe_fra")
    Double importeFra;

    //ClienteOutputDto clienteOutputDto;

    //List<LineaOutputDto> lineaOutputDtoList;

    // Muchas facturas tienen un cliente
    /*@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    Cliente cliente;*/
}
