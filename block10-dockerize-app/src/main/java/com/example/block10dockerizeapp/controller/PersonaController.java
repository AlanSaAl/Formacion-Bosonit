package com.example.block10dockerizeapp.controller;

import com.example.block10dockerizeapp.application.IPersonaService;
import com.example.block10dockerizeapp.controller.dto.PersonaInputDto;
import com.example.block10dockerizeapp.controller.dto.PersonaOutputDto;
import com.example.block10dockerizeapp.domain.Persona;
import com.example.block10dockerizeapp.mapper.IPersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persona")
public class PersonaController {
    @Autowired
    IPersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personaInputDto) {
        return ResponseEntity.ok().body(IPersonaMapper.mapper.personaToPersonaOuputDto(personaService.addPersona(personaInputDto)));
    }

    @GetMapping("{idPersona}")
    public PersonaOutputDto getPersonaById(@PathVariable int idPersona) {
        return IPersonaMapper.mapper.personaToPersonaOuputDto(personaService.getPersonaById(idPersona));
    }

    @GetMapping
    public List<PersonaOutputDto> getAllPersonas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                 @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return personaService.getAllPersonas(pageNumber, pageSize).stream().map(IPersonaMapper.mapper::personaToPersonaOuputDto).toList();
    }

    @PutMapping("{idPersona}")
    public ResponseEntity<PersonaOutputDto> modifyPersonaById(@PathVariable int idPersona, @RequestBody PersonaInputDto personaInputDto) {
        Persona persona = IPersonaMapper.mapper.personaInputDtoToPersona(personaInputDto);
        return ResponseEntity.ok().body(IPersonaMapper.mapper.personaToPersonaOuputDto(personaService.modifyPersonaById(idPersona, persona)));
    }

    @DeleteMapping("{idPersona}")
    public void deletePersonaById(@PathVariable int idPersona) {
        personaService.deletePersonaById(idPersona);
    }
}
