package com.example.block11uploaddownloadfiles.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

// Tabla para guardar los datos del fichero

@Entity
@Data
public class DatosFichero {
    @Id
    @GeneratedValue
    private int idFichero;

    private String nombreFichero;

    private Date fechaSubida;
}
