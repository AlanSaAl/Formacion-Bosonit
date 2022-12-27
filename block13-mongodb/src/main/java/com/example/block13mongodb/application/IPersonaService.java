package com.example.block13mongodb.application;

import com.example.block13mongodb.controller.dto.PersonaInputDto;
import com.example.block13mongodb.controller.dto.PersonaOutputDto;

public interface IPersonaService {
    PersonaOutputDto addPersona(PersonaInputDto personaInput);

    PersonaOutputDto getPersonaById(String idPersona);

    Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);

    PersonaOutputDto modifyPersonaById(String idPersona, PersonaInputDto personaInput);

    void deletePersonaById(String idPersona);
}
