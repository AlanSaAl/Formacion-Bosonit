package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class PersonaServiceImpl implements IPersonaService{
    @Autowired
    IPersonaRepository personaRepository;

    public void validarDatosPersona(PersonaInputDto persona) {
        try {
            Objects.requireNonNull(persona.getUsuario(), "Usuario no puede ser nulo");
            Objects.requireNonNull(persona.getPassword(), "Password no puede ser nulo");
            Objects.requireNonNull(persona.getName(), "Name no puede ser nulo");
            Objects.requireNonNull(persona.getCompany_email(), "company_email no puede ser nulo");
            Objects.requireNonNull(persona.getPersonal_email(), "personal_email no puede ser nulo");
            Objects.requireNonNull(persona.getCity(), "city no puede ser nulo");
            Objects.requireNonNull(persona.getCreated_date(), "created_date no puede ser nulo");
        } catch (NullPointerException e) {
            throw new UnprocessableEntityException(e.getMessage());
        } if (persona.getUsuario().length() > 10) {
            throw new UnprocessableEntityException("Longitud de usuario no puede ser superior a 10 caracteres");
        }
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) {
        validarDatosPersona(persona);
        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
    }

    @Override
    public PersonaOutputDto getPersonaById(int id) {
        try {
            return personaRepository.findById(id).orElseThrow().personaToPersonaOutputDto();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id: " + id);
        }
    }

    @Override
    public List<PersonaOutputDto> getPersonaByUsuario(String usuario) {
        return personaRepository.findByUsuario(usuario).stream().map(Persona::personaToPersonaOutputDto).toList();
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent().stream().map(Persona::personaToPersonaOutputDto).toList();
    }

    @Override
    public void deletePersona(int id) {
        try {
            personaRepository.findById(id).orElseThrow();
            personaRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}
