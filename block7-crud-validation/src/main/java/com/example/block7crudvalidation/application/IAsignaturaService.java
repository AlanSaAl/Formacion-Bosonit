package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.AsignaturaOutputDto;
import com.example.block7crudvalidation.controller.dto.AsignaturaInputDto;
import com.example.block7crudvalidation.domain.Asignatura;

import java.util.List;

public interface IAsignaturaService {
    Asignatura addAsignatura(AsignaturaInputDto asignaturaInput);
    Asignatura getAsignaturaById(String id);
    List<AsignaturaOutputDto> getAllAsignaturas(int pageNumber, int pageSize);
    Asignatura updateAsignatura(String id, AsignaturaInputDto asignaturaInput);
    void deleteAsignaturaById(String id);
}
