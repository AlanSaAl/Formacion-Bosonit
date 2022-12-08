package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.controller.dto.ProfesotOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.mapper.IProfesorMapper;
import com.example.block7crudvalidation.repository.IPersonaRepository;
import com.example.block7crudvalidation.repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class ProfesorServiceImpl implements IProfesorService{
    @Autowired
    IProfesorRepository profesorRepository;
    @Autowired
    IPersonaRepository personaRepository;

    public void validarDatosProfesor(ProfesorInputDto profesorInput) {
        try {
            Objects.requireNonNull(profesorInput.getBranch(), "rama no puede ser nulo");
        } catch (NullPointerException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
    }

    @Override
    public Profesor addProfesor(ProfesorInputDto profesorInput) {
        validarDatosProfesor(profesorInput);
        try {
            Persona persona = personaRepository.findById(profesorInput.getIdPersona()).orElseThrow();
            if (persona.getProfesor() != null || persona.getStudent() != null)
                throw new UnprocessableEntityException("La persona: " + profesorInput.getIdPersona() + " ya tiene datos de estudiante o profesor asignados.");
            Profesor profesor = IProfesorMapper.mapper.profesorInputDtoToProfesor(profesorInput);
            persona.setProfesor(profesor);
            profesor.setPersona(persona);
            return profesorRepository.save(profesor);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró la persona con el id: " + profesorInput.getIdPersona());
        }
    }

    @Override
    public Profesor getProfesorById(String id) {
        try {
            return profesorRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id: " + id);
        }
    }

    @Override
    public List<ProfesotOutputDto> getAllProfesores(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return profesorRepository.findAll().stream().map(IProfesorMapper.mapper::profesorToProfesorOutputDto).toList();
    }

    @Override
    public Profesor updateProfesorById(ProfesorInputDto profesorInput) {
        validarDatosProfesor(profesorInput);
        getProfesorById(profesorInput.getIdProfesor());
        return profesorRepository.save(IProfesorMapper.mapper.profesorInputDtoToProfesor(profesorInput));
    }

    @Override
    public void deleteProfesorById(String id) {

    }
}
