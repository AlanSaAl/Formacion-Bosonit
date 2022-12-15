package com.example.block10dockerizeapp.mapper;

import com.example.block10dockerizeapp.controller.dto.PersonaInputDto;
import com.example.block10dockerizeapp.controller.dto.PersonaOutputDto;
import com.example.block10dockerizeapp.domain.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IPersonaMapper {
    IPersonaMapper mapper = Mappers.getMapper(IPersonaMapper.class);

    Persona personaInputDtoToPersona(PersonaInputDto personaInputDto);
    PersonaOutputDto personaToPersonaOuputDto(Persona persona);
}
