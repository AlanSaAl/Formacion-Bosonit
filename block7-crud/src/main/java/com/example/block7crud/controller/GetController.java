package com.example.block7crud.controller;

import com.example.block7crud.application.PersonaServiceImpl;
import com.example.block7crud.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("persona")
public class GetController {
    @Autowired
    PersonaServiceImpl personaService;

    @GetMapping("id/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(personaService.getPersonaById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<PersonaOutputDto> getAllPersonas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                     @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return personaService.getAllPersonas(pageNumber, pageSize);
    }

    @GetMapping("nombre/{nombre}")
    public Iterable<PersonaOutputDto> getPersonaByNombre(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                         @RequestParam(defaultValue = "4", required = false) int pageSize,
                                                         @PathVariable String nombre) {
        return personaService.getPersonaByNombre(pageNumber, pageSize, nombre);
        //http://localhost:8080/persona/nombre/jimmy?pageNumber=0&pageSize=1
    }
}
