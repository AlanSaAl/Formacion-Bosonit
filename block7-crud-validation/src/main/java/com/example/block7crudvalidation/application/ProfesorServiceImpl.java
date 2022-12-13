package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.ProfesotOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.IProfesorRepository;
import com.example.block7crudvalidation.repository.IStudentRepository;
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
    PersonaServiceImpl personaService;
    @Autowired
    IStudentRepository studentRepository;

    public void validarDatosProfesor(Profesor profesor) {
        try {
            Objects.requireNonNull(profesor.getBranch(), "rama no puede ser nulo");
        } catch (NullPointerException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
    }

    @Override
    public Profesor addProfesor(Profesor profesor, int idPersona) {
        validarDatosProfesor(profesor);

        try {
            Persona persona = personaService.getPersonaById(idPersona);
            if (persona.getProfesor() != null || persona.getStudent() != null)
                throw new UnprocessableEntityException("La persona: " + idPersona + " ya tiene datos de estudiante o profesor asignados.");
            persona.setProfesor(profesor);
            profesor.setPersona(persona);
            return profesorRepository.save(profesor);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró la persona con el id: " + idPersona);
        }
    }

    @Override
    public Profesor getProfesorById(String id) {
        try {
            return profesorRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id de profesor: " + id);
        }
    }

    @Override
    public List<ProfesotOutputDto> getAllProfesores(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return profesorRepository.findAll().stream().map(Profesor::profesorToProfesorOutputDto).toList();
    }

    @Override
    public Profesor updateProfesorById(String idProf, Profesor profesor) {
        validarDatosProfesor(profesor);
        getProfesorById(idProf);
        return profesorRepository.save(profesor);
    }

    @Override
    public void deleteProfesorById(String id) {
        Profesor profesor = getProfesorById(id);
        profesor.getStudentList().forEach(student -> {
            student.setProfesor(null);
            studentRepository.save(student);
        });
        profesorRepository.deleteById(id);
    }
}
