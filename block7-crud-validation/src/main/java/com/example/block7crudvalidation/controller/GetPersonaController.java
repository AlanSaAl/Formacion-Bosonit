package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persona")
public class GetPersonaController {
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
}
