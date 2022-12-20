package com.example.block11uploaddownloadfiles.application;

import com.example.block11uploaddownloadfiles.controller.dto.DatosFicheroInputDto;
import com.example.block11uploaddownloadfiles.controller.dto.DatosFicheroOutputDto;

public interface DatosFicheroService {
    DatosFicheroOutputDto addMetadatos(DatosFicheroInputDto datosFicheroInputDto);

    DatosFicheroOutputDto getDatosFicheroById(int id);
}
