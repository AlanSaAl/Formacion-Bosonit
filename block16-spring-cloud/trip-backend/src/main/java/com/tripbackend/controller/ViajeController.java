package com.tripbackend.controller;

import com.tripbackend.application.IViajeService;
import com.tripbackend.controller.dto.ViajeInputDto;
import com.tripbackend.controller.dto.ViajeOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("viaje")
public class ViajeController {
    @Autowired
    IViajeService viajeService;

    @PostMapping
    public ResponseEntity<ViajeOutputDto> addViaje(@RequestBody ViajeInputDto viajeInput) {
        return ResponseEntity.ok().body(viajeService.addViaje(viajeInput));
    }

    @PutMapping("addPasajero/{idViaje}/{idPasajero}")
    public ResponseEntity<ViajeOutputDto> addPasajero(@PathVariable int idViaje, @PathVariable int idPasajero) {
        return ResponseEntity.ok().body(viajeService.addPasajero(idViaje, idPasajero));
    }

    @GetMapping
    public Iterable<ViajeOutputDto> getAllViajes(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                 @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return viajeService.getAllViajes(pageNumber, pageSize);
    }

    @GetMapping("obtener/{idViaje}")
    public ViajeOutputDto getViajeById(@PathVariable int idViaje) {
        return viajeService.getViajeById(idViaje);
    }

    @PutMapping("modificar/{idViaje}")
    public ResponseEntity<ViajeOutputDto> modifyViajeById(@PathVariable int idViaje, @RequestBody ViajeInputDto viajeInput) {
        return ResponseEntity.ok().body(viajeService.modifyViajeById(idViaje, viajeInput));
    }

    @DeleteMapping("borrar/{idViaje}")
    public void deleteViajeById(@PathVariable int idViaje) {
        viajeService.deleteViajeById(idViaje);
    }
}
