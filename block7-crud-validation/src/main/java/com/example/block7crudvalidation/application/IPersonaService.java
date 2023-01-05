package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;

import java.util.HashMap;
import java.util.List;

public interface IPersonaService {
    PersonaOutputDto addPersona(PersonaInputDto persona);
    Persona getPersonaById(int idPersona);
    PersonaOutputDto getPersonaByUsuario(String usuario);
    List<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDto modifyPersonaById(int idPersona, PersonaInputDto personaInput);
    void deletePersona(int idPersona);
    Iterable<PersonaOutputDto> getPersonaByFields(HashMap<String, Object> conditions, String orderBy, int pageNumber, int pageSize);
}
