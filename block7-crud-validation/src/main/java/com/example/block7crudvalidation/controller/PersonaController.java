package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.IPersonaService;
import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.controller.dto.ProfesotOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.feign.IProfesorFeign;
import com.example.block7crudvalidation.mapper.IPersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("persona")
public class PersonaController {
    @Autowired
    IPersonaService personaService;
    @Autowired
    IProfesorFeign profesorFeign;

    @CrossOrigin(origins = "https://cdpn.io/")
    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personaInput) {
        return ResponseEntity.ok().body(personaService.addPersona(personaInput));
    }

    @GetMapping("id/{id}")
    public PersonaOutputDto getPersonaById(@PathVariable int id, @RequestParam(value = "outputType", defaultValue = "simple") String outputType) {
        return Objects.equals(outputType, "simple") ? IPersonaMapper.mapper.personaToPersonaOutputDto(personaService.getPersonaById(id)) :
                Objects.equals(outputType, "full") ? personaService.getPersonaById(id).personaToPersonaFullOutputDto() : null;
    }

    @GetMapping("usuario/{usuario}")
    public PersonaOutputDto getPersonaByUsuario(@PathVariable String usuario) {
        return personaService.getPersonaByUsuario(usuario);
    }

    @CrossOrigin(origins = "https://cdpn.io/")
    @GetMapping
    public List<PersonaOutputDto> getAllPersonas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                     @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return personaService.getAllPersonas(pageNumber, pageSize);
    }

    @PutMapping("actualizar/{idPersona}")
    public ResponseEntity<PersonaOutputDto> modifyPersonaById(@PathVariable int idPersona, @RequestBody PersonaInputDto personaInput) {
        return ResponseEntity.ok().body(personaService.modifyPersonaById(idPersona, personaInput));

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<PersonaOutputDto> deletePersona(@PathVariable int id) {
        Persona persona = personaService.getPersonaById(id);
        personaService.deletePersona(id);
        return ResponseEntity.ok().body(IPersonaMapper.mapper.personaToPersonaOutputDto(persona));
    }

    @GetMapping("profesor/{id}")
    public ProfesotOutputDto getProfesor(@PathVariable String id) {
        return profesorFeign.getProfesorById(id, "simple");
    }

    @GetMapping("customquery")
    public Iterable<PersonaOutputDto> getPersonaByFields(@RequestParam(required = false) String usuario,
                                                         @RequestParam(required = false) String name,
                                                         @RequestParam(required = false) String surname,
                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDate,
                                                         @RequestParam(required = false, defaultValue = "name") String orderBy,
                                                         @RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                         @RequestParam(defaultValue = "4", required = false) int pageSize) {
        HashMap<String, Object> conditions = new HashMap<>();

        if(usuario != null) conditions.put("usuario", usuario);
        if(name != null) conditions.put("name", name);
        if(surname != null) conditions.put("surname", surname);
        if(createdDate != null) conditions.put("created_date", createdDate);

        return personaService.getPersonaByFields(conditions, orderBy, pageNumber, pageSize);
    }
}