package com.example.block7crudvalidation;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.mapper.IPersonaMapper;
import com.example.block7crudvalidation.repository.IPersonaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    IPersonaRepository personaRepository;
    @Autowired
    ObjectMapper objectMapper;

    Persona crearPersona() {
        Persona persona = new Persona();

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

        return personaRepository.save(persona);
    }

    @AfterEach
    void eliminarObjeto() {
        personaRepository.deleteAll();
    }

    @Test
    @DisplayName("Guardando los datos de una persona")
    void addPersona() throws Exception{
        PersonaInputDto personaEsperado = new PersonaInputDto();

        personaEsperado.setUsuario("jimmy");
        personaEsperado.setPassword("1234");
        personaEsperado.setName("jimmy");
        personaEsperado.setSurname("sandoval");
        personaEsperado.setCompany_email("comapany@email.com");
        personaEsperado.setPersonal_email("personal@email.com");
        personaEsperado.setCity("edomex");
        personaEsperado.isActive();
        personaEsperado.setCreated_date(LocalDate.of(2022, 11, 02));
        personaEsperado.setImagen_url("/image1.jpg");
        personaEsperado.setTermination_date(LocalDate.of(2022, 02, 03));

        MvcResult mvcResult = this.mockMvc.perform(post("/persona")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(personaEsperado)))
                .andExpect(status().isOk()).andReturn();

        assertThat(personaRepository.findAll().size()).isOne();
    }

    @Test
    @DisplayName("Obteniendo los datos de una persona por su id")
    void getPersonaById() throws Exception {
        Persona persona = crearPersona();
        PersonaOutputDto personaEsperadoOut = IPersonaMapper.mapper.personaToPersonaOutputDto(persona);

        MvcResult mvcResult = this.mockMvc.perform(get("/persona/id/{id}", persona.getId())).andExpect(status()
                .isOk()).andReturn();

        String personaEsperado = objectMapper.writeValueAsString(personaEsperadoOut);
        String personaObtenido = mvcResult.getResponse().getContentAsString();

        assertEquals(personaEsperado, personaObtenido);
    }

    @Test
    @DisplayName("Obteniendo los datos de una persona por su usuario")
    void getPersonaByUsuario() throws Exception {
        Persona persona = crearPersona();
        PersonaOutputDto personaEsperadoOut = IPersonaMapper.mapper.personaToPersonaOutputDto(persona);

        MvcResult mvcResult = this.mockMvc.perform(get("/persona/usuario/{usuario}", persona.getUsuario()))
                .andExpect(status().isOk()).andReturn();

        String personaEsperado = objectMapper.writeValueAsString(personaEsperadoOut);
        String personaObtenido = mvcResult.getResponse().getContentAsString();

        assertEquals(personaEsperado, personaObtenido);
    }

    @Test
    @DisplayName("Obteniendo los datos de todas las personas")
    void getAllPersonas() throws Exception {
        Persona personaUno = crearPersona();
        Persona personaDos = crearPersona();

        MvcResult mvcResult = this.mockMvc.perform(get("/persona"))
                .andExpect(status().isOk()).andReturn();

        String resultado = mvcResult.getResponse().getContentAsString();
        Persona[] listaPersonas = objectMapper.readValue(resultado, Persona[].class);

        assertTrue(listaPersonas.length > 0);
    }

    @Test
    @DisplayName("Modificando los datos de la persona por su id")
    void modifyPersonaById() throws Exception {
        Persona persona = crearPersona();
        PersonaInputDto personaModificado = new PersonaInputDto();

        personaModificado.setId(1);
        personaModificado.setUsuario("alan");
        personaModificado.setPassword("4321");
        personaModificado.setName("alan");
        personaModificado.setSurname("sandoval");
        personaModificado.setCompany_email("comapany@email.com");
        personaModificado.setPersonal_email("personal@email.com");
        personaModificado.setCity("edomex");
        personaModificado.isActive();
        personaModificado.setCreated_date(LocalDate.of(2022, 11, 02));
        personaModificado.setImagen_url("/image1.jpg");
        personaModificado.setTermination_date(LocalDate.of(2022, 02, 03));

        this.mockMvc.perform(put("/persona/actualizar/{idPersona}", persona.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personaModificado)))
                .andExpect(status().isOk()).andReturn();

        Persona personaObtenido = personaRepository.findById(persona.getId()).get();
        personaObtenido.setId(1);
        Persona personaEsperado = IPersonaMapper.mapper.personaInputDtoToPersona(personaModificado);
        String personaObtenidoJson = objectMapper.writeValueAsString(personaObtenido);
        String personaEsperadoJson = objectMapper.writeValueAsString(personaEsperado);

        assertEquals(personaEsperadoJson, personaObtenidoJson);
    }

    @Test
    @DisplayName("Borrando persona por su id")
    void deletePersonaById() throws Exception {
        Persona persona = crearPersona();

        this.mockMvc.perform(delete("/persona/delete/{id}", persona.getId()))
                .andExpect(status().isOk());

        assertThat(personaRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("Obteniendo datos de la persona por diferentes campos")
    void getPersonaByFields() throws Exception {
        Persona persona = crearPersona();

        MvcResult mvcResult = this.mockMvc.perform(get("/persona/")
                        .param("usuario", persona.getUsuario())
                        .param("name", persona.getName())
                        .param("surname", persona.getSurname())
                        .param("created_date", LocalDate.of(2022, 11, 02).toString()))
                .andExpect(status().isOk()).andReturn();

        String resultado = mvcResult.getResponse().getContentAsString();
        PersonaOutputDto[] listaPersonas = objectMapper.readValue(resultado, PersonaOutputDto[].class);

        String personaObtenidoJson = objectMapper.writeValueAsString(listaPersonas[0]);
        String personaEsperadoJson = objectMapper.writeValueAsString(IPersonaMapper.mapper.personaToPersonaOutputDto(persona));

        assertTrue(listaPersonas.length > 0);
        assertEquals(personaObtenidoJson, personaEsperadoJson);
    }
}