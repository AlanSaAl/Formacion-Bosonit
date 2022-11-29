package com.example.block7crud.application;

import com.example.block7crud.controller.dto.PersonaInputDto;
import com.example.block7crud.controller.dto.PersonaOutputDto;

public interface IPersonaService {
    PersonaOutputDto addPersona(PersonaInputDto persona);
    PersonaOutputDto updatePersona(int id, String nombre, String edad, String poblacion);
    void deletePersonaById(int id);
    PersonaOutputDto getPersonaById(int id);
    Iterable<PersonaOutputDto> getPersonaByNombre(int pageNumber, int pageSize, String nombre);
    Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);

}
