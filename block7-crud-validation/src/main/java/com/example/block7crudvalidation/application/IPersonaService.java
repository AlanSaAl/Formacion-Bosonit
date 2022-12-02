package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;

import java.util.List;

public interface IPersonaService {
    PersonaOutputDto addPersona(PersonaInputDto persona);
    PersonaOutputDto getPersonaById(int id);
    List<PersonaOutputDto> getPersonaByUsuario(String usuario);
    Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);
    void deletePersona(int id);
}
