package com.example.block10dockerizeapp.application;

import com.example.block10dockerizeapp.controller.dto.PersonaInputDto;
import com.example.block10dockerizeapp.domain.Persona;

import java.util.List;

public interface IPersonaService {
    Persona addPersona(PersonaInputDto personaInputDto);
    Persona getPersonaById(int idPersona);
    List<Persona> getAllPersonas(int pageNumber, int pageSize);
    Persona modifyPersonaById(int idPersona, Persona persona);
    void deletePersonaById(int idPersona);
}
