package com.tripbackend.domain;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para que el id se genere del lado de la base de datos
    private int idCliente;

    private String nombre;

    private String apellido;

    private int edad;

    private String email;

    private String telefono;

    //@ManyToMany(mappedBy = "pasajeros")
    //private List<Viaje> viajes;
}
