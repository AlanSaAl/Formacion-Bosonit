package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.controller.dto.ProfesotOutputDto;
import com.example.block7crudvalidation.domain.Profesor;

import java.util.List;

public interface IProfesorService {
    Profesor addProfesor(ProfesorInputDto profesorInput);
    Profesor getProfesorById(String id);
    List<ProfesotOutputDto> getAllProfesores(int pageNumber, int pageSize);
    Profesor updateProfesorById(ProfesorInputDto profesorInput);
    void deleteProfesorById(String id);
}
