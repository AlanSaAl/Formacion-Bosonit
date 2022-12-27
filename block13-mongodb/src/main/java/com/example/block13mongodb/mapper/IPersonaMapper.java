package com.example.block13mongodb.mapper;

import com.example.block13mongodb.controller.dto.PersonaInputDto;
import com.example.block13mongodb.controller.dto.PersonaOutputDto;
import com.example.block13mongodb.domain.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IPersonaMapper {
    IPersonaMapper mapper = Mappers.getMapper(IPersonaMapper.class);

    Persona personaInputDtoToPersona(PersonaInputDto personaInput);

    PersonaOutputDto personaToPersonaOutputDto(Persona persona);
}
