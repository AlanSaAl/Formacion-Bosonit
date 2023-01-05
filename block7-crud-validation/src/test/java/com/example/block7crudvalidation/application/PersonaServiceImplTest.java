package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.mapper.IPersonaMapper;
import com.example.block7crudvalidation.repository.IPersonaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonaServiceImplTest {
    @Mock
    IPersonaRepository personaRepository;
    @InjectMocks
    PersonaServiceImpl personaService;

    Persona crearPersona() {
        Persona persona = new Persona();

        persona.setId(1);
        persona.setUsuario("jimmy");
        persona.setPassword("1234");
        persona.setName("jimmy");
        persona.setSurname("sandoval");
        persona.setCompany_email("comapany@email.com");
        persona.setPersonal_email("personal@email.com");
        persona.setCity("edomex");
        persona.isActive();
        persona.setCreated_date(LocalDate.of(2022, 11, 02));
        persona.setImagen_url("/image1.jpg");
        persona.setTermination_date(LocalDate.of(2022, 02, 03));
        return persona;
    }

    PersonaInputDto crearRequest() {
        PersonaInputDto personaInput = new PersonaInputDto();

        personaInput.setId(1);
        personaInput.setUsuario("jimmy");
        personaInput.setPassword("1234");
        personaInput.setName("jimmy");
        personaInput.setSurname("sandoval");
        personaInput.setCompany_email("comapany@email.com");
        personaInput.setPersonal_email("personal@email.com");
        personaInput.setCity("edomex");
        personaInput.isActive();
        personaInput.setCreated_date(LocalDate.of(2022, 11, 02));
        personaInput.setImagen_url("/image1.jpg");
        personaInput.setTermination_date(LocalDate.of(2022, 02, 03));
        return personaInput;
    }

    @Test
    @DisplayName("Guardando los datos de una persona")
    void addPersona() {
        PersonaInputDto personaInput = crearRequest();

        when(personaRepository.save(any(Persona.class))).thenReturn(new Persona());

        PersonaOutputDto personaObtenido = personaService.addPersona(personaInput);

        assertThat(personaObtenido.getId()).isEqualTo(personaInput.getId());
        verify(personaRepository, atLeastOnce()).save(any(Persona.class));
    }

    @Test
    @DisplayName("Guardando una persona con datos no validos")
    void addPersonaNoValida() {
        PersonaInputDto personaInput = crearRequest();
        personaInput.setCreated_date(null);
        UnprocessableEntityException excepcionEsperada = new UnprocessableEntityException("created_date no puede ser nulo");

        UnprocessableEntityException excepcionObtenida = assertThrows(UnprocessableEntityException.class, () -> personaService.addPersona(personaInput));
        assertEquals(excepcionEsperada.getMessage(), excepcionObtenida.getMessage());
    }

    @Test
    @DisplayName("Guardando una persona con usuario mayor a 10 caracteres")
    void addPersonaUsuarioNoValido() {
        PersonaInputDto personaInput = crearRequest();
        personaInput.setUsuario("12345678901");
        UnprocessableEntityException excepcionEsperada = new UnprocessableEntityException("Longitud de usuario no puede ser superior a 10 caracteres");

        UnprocessableEntityException excepcionObtenida = assertThrows(UnprocessableEntityException.class, () -> personaService.addPersona(personaInput));
        assertEquals(excepcionEsperada.getMessage(), excepcionObtenida.getMessage());
    }

    @Test
    @DisplayName("Obteniendo los datos de una persona por su id")
    void getPersonaById() {
        Persona personaEsperado = crearPersona();

        when(personaRepository.findById(personaEsperado.getId())).thenReturn(Optional.of(personaEsperado));
        Persona personaObtenido = assertDoesNotThrow(() -> personaService.getPersonaById(personaEsperado.getId()));
        assertEquals(personaEsperado, personaObtenido);
        verify(personaRepository, atLeastOnce()).findById(personaEsperado.getId());
    }

    @Test
    @DisplayName("Buscando una persona que no existe")
    void getPersonaByIdNotFound() {
        int idPersona = 1;
        EntityNotFoundException excepcionEsperada = new EntityNotFoundException("No se encontró el id: " + idPersona);

        when(personaRepository.findById(idPersona)).thenReturn(Optional.empty());
        EntityNotFoundException exceptionObtenida = assertThrows(EntityNotFoundException.class, () -> personaService.getPersonaById(idPersona));
        assertEquals(excepcionEsperada.getMessage(), exceptionObtenida.getMessage());
    }

    @Test
    @DisplayName("Obteniendo los datos de una persona por su usuario")
    void getPersonaByUsuario() {
        Persona persona = crearPersona();
        PersonaOutputDto personaEsperado = IPersonaMapper.mapper.personaToPersonaOutputDto(persona);

        when(personaRepository.findByUsuario(persona.getUsuario())).thenReturn(persona);
        PersonaOutputDto personaObtenido = assertDoesNotThrow(() -> personaService.getPersonaByUsuario(persona.getUsuario()));
        assertEquals(personaEsperado, personaObtenido);
        verify(personaRepository, atLeastOnce()).findByUsuario(personaEsperado.getUsuario());
    }

    @Test
    @DisplayName("Obteniendo los datos de todas las personas")
    void getAllPersonas() {
        int pageNumber = 0;
        int pageSize = 10;
        Persona personaUno = crearPersona();
        Persona personaDos = crearPersona();
        Page<Persona> listaPersonas = new PageImpl<>(List.of(personaUno, personaDos));

        when(personaRepository.findAll(PageRequest.of(pageNumber, pageSize))).thenReturn(listaPersonas);

        List<PersonaOutputDto> listaPersonasObtenida = personaService.getAllPersonas(pageNumber, pageSize);

        assertTrue(listaPersonasObtenida.size() > 0);
    }

    @Test
    void modifyPersonaById() {
        Persona persona = crearPersona();
        PersonaInputDto personaRequest = crearRequest();
        PersonaOutputDto personaEsperado = IPersonaMapper.mapper.personaToPersonaOutputDto(persona);

        when(personaRepository.findById(persona.getId())).thenReturn(Optional.of(persona));
        when(personaRepository.save(any(Persona.class))).thenReturn(new Persona());

        PersonaOutputDto personaObtenido = assertDoesNotThrow(() -> personaService.modifyPersonaById(persona.getId(), personaRequest));

        assertEquals(personaEsperado, personaObtenido);

        verify(personaRepository, atLeastOnce()).findById(persona.getId());
        verify(personaRepository, atLeastOnce()).save(any(Persona.class));
    }

    @Test
    @DisplayName("Borrando persona por su id")
    void deletePersonaById() {
        Persona persona = crearPersona();

        when(personaRepository.findById(persona.getId())).thenReturn(Optional.of(persona));
        doNothing().when(personaRepository).deleteById(persona.getId());

        assertDoesNotThrow(() -> personaService.deletePersona(persona.getId()));

        verify(personaRepository, atLeastOnce()).findById(persona.getId());
        verify(personaRepository, atLeastOnce()).deleteById(persona.getId());
    }
}