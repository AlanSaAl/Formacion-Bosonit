package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.ProfesorServiceImpl;
import com.example.block7crudvalidation.controller.dto.ProfesorFullOutputDto;
import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.controller.dto.ProfesotOutputDto;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.mapper.IProfesorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("profesor")
public class ProfesorController {
    @Autowired
    ProfesorServiceImpl profesorService;

    @PostMapping
    public ResponseEntity<ProfesorFullOutputDto> addProfesor(@RequestBody ProfesorInputDto profesorInput) {
        Profesor profesor = profesorService.addProfesor(profesorInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(IProfesorMapper.mapper.profesorToProfesorFullOutputDto(profesor));
    }

    @GetMapping("{id}")
    public ProfesotOutputDto getProfesorById(@PathVariable String id, @RequestParam(value = "outputType", defaultValue = "simple") String outputType) {
        return Objects.equals(outputType, "simple") ? IProfesorMapper.mapper.profesorToProfesorOutputDto(profesorService.getProfesorById(id)) :
                Objects.equals(outputType, "full") ? IProfesorMapper.mapper.profesorToProfesorFullOutputDto(profesorService.getProfesorById(id)) : null;
    }

    @GetMapping
    public List<ProfesotOutputDto> getAllProfesores(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                    @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return profesorService.getAllProfesores(pageNumber, pageSize);
    }

    @PutMapping
    public ResponseEntity<ProfesotOutputDto> updateProfesorById(@RequestBody ProfesorInputDto profesorInput) {
        return ResponseEntity.ok().body(IProfesorMapper.mapper.profesorToProfesorOutputDto(profesorService.updateProfesorById(profesorInput)));
    }
}
