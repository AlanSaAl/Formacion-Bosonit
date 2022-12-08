package com.example.block7crudvalidation.mapper;

import com.example.block7crudvalidation.application.IProfesorService;
import com.example.block7crudvalidation.controller.dto.ProfesorFullOutputDto;
import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.controller.dto.ProfesotOutputDto;
import com.example.block7crudvalidation.domain.Profesor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IProfesorMapper {
    IProfesorMapper mapper = Mappers.getMapper(IProfesorMapper.class);
    Profesor profesorInputDtoToProfesor(ProfesorInputDto profesorInput);
    ProfesotOutputDto profesorToProfesorOutputDto(Profesor profesor);
    ProfesorFullOutputDto profesorToProfesorFullOutputDto(Profesor profesor);
}
