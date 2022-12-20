package com.example.block11uploaddownloadfiles.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosFicheroInputDto {
    private String nombreFichero;

    private Date fechaSubida;
}
