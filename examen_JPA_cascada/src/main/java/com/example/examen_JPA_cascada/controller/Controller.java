package com.example.examen_JPA_cascada.controller;

import com.example.examen_JPA_cascada.application.LineasFraServiceImpl;
import com.example.examen_JPA_cascada.application.ICabeceraFraService;
import com.example.examen_JPA_cascada.controller.dto.FacturaOutputDto;
import com.example.examen_JPA_cascada.controller.dto.LineaInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("factura")
public class Controller {
    @Autowired
    ICabeceraFraService cabeceraFraService;

    @Autowired
    LineasFraServiceImpl lineasFraService;

    @GetMapping
    public List<FacturaOutputDto> getAllFacturas() {
        return cabeceraFraService.getAllFacturas();
    }

    @DeleteMapping("{id}")
    public void deleteFacturaById(@PathVariable int id) {
        cabeceraFraService.deleteFacturaById(id);
    }

    @PostMapping("linea/{idFra}")
    public ResponseEntity<?> addLineaToFactura(@RequestBody LineaInputDto lineaInputDto) {
        try {
            cabeceraFraService.getFacturaById(lineaInputDto.getIdFra());
            List<LineaInputDto> lineaInputDtoList = new ArrayList<>();
            lineaInputDtoList.add(lineaInputDto);
            return ResponseEntity.ok().body(lineasFraService.addLineaFra(lineaInputDtoList));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Factura con el id " + lineaInputDto.getIdFra() + " no existe");
        }
    }
}
