package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;

import java.util.List;

public interface IPersonaService {
    Persona addPersona(PersonaInputDto persona);
    Persona getPersonaById(int id);
    Persona getPersonaByUsuario(String usuario);
    List<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);
    void deletePersona(int id);
}
