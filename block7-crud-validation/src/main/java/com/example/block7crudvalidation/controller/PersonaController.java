package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("persona")
public class PersonaController {
    @Autowired
    PersonaServiceImpl personaService;

    @GetMapping("id/{id}")
    public PersonaOutputDto getPersonaById(@PathVariable int id) {
        return personaService.getPersonaById(id);
    }

    @GetMapping("usuario/{usuario}")
    public List<PersonaOutputDto> getPersonaByUsuario(@PathVariable String usuario) {
        return personaService.getPersonaByUsuario(usuario);
    }

    @GetMapping
    public Iterable<PersonaOutputDto> getAllPersonas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                     @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return personaService.getAllPersonas(pageNumber, pageSize);
    }

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.addPersona(persona));
    }

    @PutMapping
    public ResponseEntity<PersonaOutputDto> updatePersona(@RequestBody PersonaInputDto persona) {
        try {
            personaService.getPersonaById(persona.getId());
            return ResponseEntity.ok().body(personaService.addPersona(persona));
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Persona no encontrada");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<PersonaOutputDto> deletePersona(@PathVariable int id) {
        PersonaOutputDto persona = personaService.getPersonaById(id);
        personaService.deletePersona(id);
        return ResponseEntity.ok().body(persona);
    }
}
