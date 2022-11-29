package com.example.block7crud.controller;

import com.example.block7crud.application.PersonaServiceImpl;
import com.example.block7crud.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("persona")
public class DeleteController {
    @Autowired
    PersonaServiceImpl personaService;

    @DeleteMapping("{id}")
    public ResponseEntity<PersonaOutputDto> deletePersonById(@PathVariable int id) {
        try {
            PersonaOutputDto persona = personaService.getPersonaById(id);
            personaService.deletePersonaById(id);
            return ResponseEntity.ok().body(persona);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
