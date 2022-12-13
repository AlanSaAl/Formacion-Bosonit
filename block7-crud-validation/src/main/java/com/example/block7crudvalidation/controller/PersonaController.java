package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.mapper.IPersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("persona")
public class PersonaController {
    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personaInput) {
        return ResponseEntity.ok().body(IPersonaMapper.mapper.personaToPersonaOutputDto(personaService.addPersona(personaInput)));
    }

    @GetMapping("id/{id}")
    public PersonaOutputDto getPersonaById(@PathVariable int id, @RequestParam(value = "outputType", defaultValue = "simple") String outputType) {
        return Objects.equals(outputType, "simple") ? IPersonaMapper.mapper.personaToPersonaOutputDto(personaService.getPersonaById(id)) :
                Objects.equals(outputType, "full") ? personaService.getPersonaById(id).personaToPersonaFullOutputDto() : null;
    }

    @GetMapping("usuario/{usuario}")
    public PersonaOutputDto getPersonaByUsuario(@PathVariable String usuario) {
        return IPersonaMapper.mapper.personaToPersonaOutputDto(personaService.getPersonaByUsuario(usuario));
    }

    @GetMapping
    public List<PersonaOutputDto> getAllPersonas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                     @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return personaService.getAllPersonas(pageNumber, pageSize);
    }

    @PutMapping
    public ResponseEntity<PersonaOutputDto> updatePersona(@RequestBody PersonaInputDto personaInput) {
        personaService.getPersonaById(personaInput.getId());
        return ResponseEntity.ok().body(IPersonaMapper.mapper.personaToPersonaOutputDto(personaService.addPersona(personaInput)));

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<PersonaOutputDto> deletePersona(@PathVariable int id) {
        Persona persona = personaService.getPersonaById(id);
        personaService.deletePersona(id);
        return ResponseEntity.ok().body(IPersonaMapper.mapper.personaToPersonaOutputDto(persona));
    }
}
