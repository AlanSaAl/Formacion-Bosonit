package com.example.block10dockerizeapp.application;

import com.example.block10dockerizeapp.controller.dto.PersonaInputDto;
import com.example.block10dockerizeapp.domain.Persona;
import com.example.block10dockerizeapp.exceptions.EntityNotFoundException;
import com.example.block10dockerizeapp.mapper.IPersonaMapper;
import com.example.block10dockerizeapp.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonaServiceImpl implements IPersonaService{
    @Autowired
    IPersonaRepository personaRepository;

    @Override
    public Persona addPersona(PersonaInputDto personaInputDto) {
        Persona persona = IPersonaMapper.mapper.personaInputDtoToPersona(personaInputDto);
        return personaRepository.save(persona);
    }

    @Override
    public Persona getPersonaById(int idPersona) {
        try {
            return personaRepository.findById(idPersona).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id: " + idPersona);
        }
    }

    @Override
    public List<Persona> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).toList();
    }

    @Override
    public Persona modifyPersonaById(int idPersona, Persona persona) {
        getPersonaById(idPersona);
        persona.setId(idPersona);
        return personaRepository.save(persona);
    }

    @Override
    public void deletePersonaById(int idPersona) {
        getPersonaById(idPersona);
        personaRepository.deleteById(idPersona);
    }
}
