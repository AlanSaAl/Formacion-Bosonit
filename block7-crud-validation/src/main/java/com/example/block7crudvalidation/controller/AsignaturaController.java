package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.AsignaturaServiceImpl;
import com.example.block7crudvalidation.controller.dto.AsignaturaInputDto;
import com.example.block7crudvalidation.controller.dto.AsignaturaOutputDto;
import com.example.block7crudvalidation.domain.Asignatura;
import com.example.block7crudvalidation.mapper.IAsignaturaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("asignatura")
public class AsignaturaController {
    @Autowired
    AsignaturaServiceImpl asignaturaService;

    @PostMapping
    public ResponseEntity<AsignaturaOutputDto> addAsignatura(@RequestBody AsignaturaInputDto asignaturaInput) {
        Asignatura asignatura = asignaturaService.addAsignatura(asignaturaInput);
        return ResponseEntity.ok().body(IAsignaturaMapper.mapper.asignaturaToAsignaturaOutputDto(asignatura));
    }

    @GetMapping
    public List<AsignaturaOutputDto> getAllAsignaturas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                       @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return asignaturaService.getAllAsignaturas(pageNumber, pageSize);
    }

    @GetMapping("{id}")
    public AsignaturaOutputDto getAsignaturaById(@PathVariable String id) {
        return IAsignaturaMapper.mapper.asignaturaToAsignaturaOutputDto(asignaturaService.getAsignaturaById(id));
    }

    @PutMapping
    public ResponseEntity<AsignaturaOutputDto> updateAsignatura(@PathVariable String id, @RequestBody AsignaturaInputDto asignaturaInput) {
        return ResponseEntity.ok().body(IAsignaturaMapper.mapper.asignaturaToAsignaturaOutputDto(asignaturaService.updateAsignatura(id, asignaturaInput)));
    }
}