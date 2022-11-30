package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("persona")
public class PostPersonaController {
    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona) throws Exception {
        String error = "";
        if (persona.getUsuario() == null) {
            error = "Usuario no puede ser nulo";
            throw new Exception(error);
        } else if (persona.getUsuario().length() > 10) {
            error = "Longitud de usuario no puede ser superior a 10 caracteres";
            throw new Exception(error);
        } else if (persona.getPassword() == null) {
            error = "Password no puede ser nulo";
            throw new Exception(error);
        } else if (persona.getName() == null) {
            error = "Name no puede ser nulo";
            throw new Exception(error);
        } else if (persona.getCompany_email() == null) {
            error = "company_email no puede ser nulo";
            throw new Exception(error);
        } else if (persona.getPersonal_email() == null) {
            error = "personal_email no puede ser nulo";
            throw new Exception(error);
        } else if (persona.getCity() == null) {
            error = "city no puede ser nulo";
            throw new Exception(error);
        } else if (persona.getCreated_date() == null) {
            error = "created_date no puede ser nulo";
            throw new Exception(error);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(personaService.addPersona(persona));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handleValidationExceptions(Exception ex) {
        Logger logger = LoggerFactory.getLogger(PostPersonaController.class);
        String error = "Error: " + ex.getMessage();
        logger.error(error);
        return error;
    }
}
