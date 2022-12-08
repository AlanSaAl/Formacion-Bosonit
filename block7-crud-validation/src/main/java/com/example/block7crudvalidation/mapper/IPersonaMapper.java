package com.example.block7crudvalidation.mapper;

import com.example.block7crudvalidation.controller.dto.PersonaFullOutputDto;
import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IPersonaMapper {
    IPersonaMapper mapper = Mappers.getMapper(IPersonaMapper.class);
    Persona personaInputDtoToPersona(PersonaInputDto personaInput);
    PersonaOutputDto personaToPersonaOutputDto(Persona persona);
    PersonaFullOutputDto personaToPersonaFullOutputDto(Persona persona);
}
