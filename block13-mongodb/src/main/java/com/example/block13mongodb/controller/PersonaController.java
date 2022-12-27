package com.example.block13mongodb.controller;

import com.example.block13mongodb.application.IPersonaService;
import com.example.block13mongodb.controller.dto.PersonaInputDto;
import com.example.block13mongodb.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("persona")
public class PersonaController {
    @Autowired
    IPersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personaInput) {
        return ResponseEntity.ok().body(personaService.addPersona(personaInput));
    }

    @GetMapping
    public Iterable<PersonaOutputDto> getAllPersonas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                     @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return personaService.getAllPersonas(pageNumber, pageSize);
    }

    @GetMapping("{idPersona}")
    public PersonaOutputDto getPersonaById(@PathVariable String idPersona) {
        return personaService.getPersonaById(idPersona);
    }

    @PutMapping("{idPersona}")
    public ResponseEntity<PersonaOutputDto> modifyPersonaById(@PathVariable String idPersona, @RequestBody PersonaInputDto personaInput) {
        return ResponseEntity.ok().body(personaService.modifyPersonaById(idPersona, personaInput));
    }

    @DeleteMapping("{idPersona}")
    public void deletePersonaById(@PathVariable String idPersona) {
        personaService.deletePersonaById(idPersona);
    }
}
