package com.example.examen_JPA_cascada.controller;

import com.example.examen_JPA_cascada.application.ICabeceraFraService;
import com.example.examen_JPA_cascada.controller.dto.FacturaOutputDto;
import com.example.examen_JPA_cascada.controller.dto.LineaInputDto;
import com.example.examen_JPA_cascada.domain.CabeceraFra;
import com.example.examen_JPA_cascada.mapper.ILineasFraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("factura")
public class Controller {
    @Autowired
    ICabeceraFraService cabeceraFraService;

    @GetMapping
    public List<FacturaOutputDto> getAllFacturas() {
        return cabeceraFraService.getAllFacturas().stream().map(CabeceraFra::cabeceraFraToFacturaOutputDto).toList();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFacturaById(@PathVariable int id) {
        try {
            cabeceraFraService.deleteFacturaById(id);
            return ResponseEntity.ok().body("Se borró la factura con el id: " + id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la factura con el id: " + id);
        }
    }

    @PostMapping("linea/{idFra}")
    public ResponseEntity<?> addLineaToFactura(@RequestBody LineaInputDto lineaInputDto, @PathVariable int idFra) {
        try {
            cabeceraFraService.getFacturaById(idFra);
            cabeceraFraService.getFacturaById(lineaInputDto.getIdFra());
            FacturaOutputDto facturaOutputDto = cabeceraFraService.addLineaToFactura(ILineasFraMapper.mapper.lineaInputDtoToLineasFra(lineaInputDto), idFra).cabeceraFraToFacturaOutputDto();
            return ResponseEntity.ok().body(facturaOutputDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el id de factura");
        }
    }
}
