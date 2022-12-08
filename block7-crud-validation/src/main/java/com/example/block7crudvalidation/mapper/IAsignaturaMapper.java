package com.example.block7crudvalidation.mapper;

import com.example.block7crudvalidation.controller.dto.AsignaturaInputDto;
import com.example.block7crudvalidation.controller.dto.AsignaturaOutputDto;
import com.example.block7crudvalidation.domain.Asignatura;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAsignaturaMapper {
    IAsignaturaMapper mapper = Mappers.getMapper(IAsignaturaMapper.class);
    Asignatura asignaturaInputDtoToAsignatura(AsignaturaInputDto asignaturaInput);
    AsignaturaOutputDto asignaturaToAsignaturaOutputDto(Asignatura asignatura);
}
