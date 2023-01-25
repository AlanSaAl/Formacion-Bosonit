package com.tripbackend.domain;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idViaje;

    private String origen;

    private String destino;

    private LocalDate fechaPartida;

    private LocalDate fechaLlegada;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "viaje_cliente",
            joinColumns = @JoinColumn(name = "idViaje"),
            inverseJoinColumns = @JoinColumn(name = "idCliente"))
    private List<Cliente> pasajeros;

    private String estatus;
}
