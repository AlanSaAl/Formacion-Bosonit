package com.example.block11uploaddownloadfiles.mapper;

import com.example.block11uploaddownloadfiles.controller.dto.DatosFicheroInputDto;
import com.example.block11uploaddownloadfiles.controller.dto.DatosFicheroOutputDto;
import com.example.block11uploaddownloadfiles.domain.DatosFichero;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DatosFicheroMapper {
    DatosFicheroMapper mapper = Mappers.getMapper(DatosFicheroMapper.class);

    DatosFichero datosFicheroInputDtoToDatosFichero(DatosFicheroInputDto datosFicheroInputDto);
    DatosFicheroOutputDto datosFicheroToDatosFicheroOutputDto(DatosFichero datosFichero);
}
