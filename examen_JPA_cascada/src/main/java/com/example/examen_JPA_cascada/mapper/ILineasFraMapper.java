package com.example.examen_JPA_cascada.mapper;

import com.example.examen_JPA_cascada.controller.dto.LineaInputDto;
import com.example.examen_JPA_cascada.controller.dto.LineaOutputDto;
import com.example.examen_JPA_cascada.domain.LineasFra;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ILineasFraMapper {
    ILineasFraMapper mapper = Mappers.getMapper(ILineasFraMapper.class);
    LineasFra lineaInputDtoToLineasFra(LineaInputDto lineaInput);
    LineaOutputDto lineasFraToLineaOutputDto(LineasFra lineasFra);
}
