package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.controller.dto.ProfesotOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.mapper.IProfesorMapper;
import com.example.block7crudvalidation.repository.IPersonaRepository;
import com.example.block7crudvalidation.repository.IProfesorRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProfesorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    IProfesorRepository profesorRepository;
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

    Pair<Profesor, Persona> crearProfesor() {
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

        Profesor profesor = new Profesor();

        profesor.setComents("Comentario");
        profesor.setBranch("Back");
        profesor.setPersona(persona);
        profesor.setStudentList(new ArrayList<>());

        persona.setProfesor(profesor);
        profesor.setPersona(persona);
        persona = personaRepository.save(persona);
        profesor = profesorRepository.save(profesor);

        return Pair.of(profesor, persona);
    }

    @AfterEach
    void eliminarObjeto() {
        personaRepository.deleteAll();
        profesorRepository.deleteAll();
    }

    @Test
    @DisplayName("Guardando datos de profesor")
    void addProfesor() throws Exception{
        Persona persona = crearPersona();
        ProfesorInputDto profesorEsperado = new ProfesorInputDto("Comentario", "Back", persona.getId());

        this.mockMvc.perform(post("/profesor")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(profesorEsperado)))
                .andExpect(status().isCreated()).andReturn();

        Profesor profesorObtenido = profesorRepository.findAll().get(0);
        assertTrue(profesorRepository.findAll().size() > 0);
        assertEquals(profesorEsperado.getBranch(), profesorObtenido.getBranch());
    }

    @Test
    @DisplayName("Guardando datos de profesor con branch nulo")
    void addProfesorBranchNoValido() throws Exception{
        Persona persona = crearPersona();
        ProfesorInputDto profesorEsperado = new ProfesorInputDto("Comentario", null, persona.getId());

        this.mockMvc.perform(post("/profesor")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(profesorEsperado)))
                .andExpect(status().isUnprocessableEntity()).andReturn();

        assertThat(profesorRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("Guardando datos de profesor con persona no valida")
    void addProfesorPersonaNoValida() throws Exception{
        int idPersona = 1;
        ProfesorInputDto profesorEsperado = new ProfesorInputDto("Comentario", "Back", idPersona);

        this.mockMvc.perform(post("/profesor")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(profesorEsperado)))
                .andExpect(status().isNotFound()).andReturn();

        assertThat(profesorRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("Obteniendo los datos de profesor por su id")
    void getProfesorById() throws Exception{
        Pair<Profesor, Persona> datosGuardados = crearProfesor();

        MvcResult mvcResult = mockMvc.perform(get("/profesor/{id}", datosGuardados.getFirst().getIdProfesor()))
                .andExpect(status().isOk()).andReturn();

        ProfesotOutputDto profesorEsperado = IProfesorMapper.mapper.profesorToProfesorOutputDto(datosGuardados.getFirst());
        ProfesotOutputDto profesorObtenido = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ProfesotOutputDto.class);

        assertEquals(profesorEsperado.getBranch(), profesorObtenido.getBranch());
    }

    @Test
    @DisplayName("Obteniendo todos los profesores")
    void getAllProfesores() throws Exception {
        Pair<Profesor, Persona> datosGuardadosUno = crearProfesor();
        Pair<Profesor, Persona> datosGuardadosDos = crearProfesor();

        MvcResult mvcResult = this.mockMvc.perform(get("/profesor"))
                .andExpect(status().isOk()).andReturn();

        String resultado = mvcResult.getResponse().getContentAsString();
        List<ProfesotOutputDto> listaProfesores = objectMapper.readValue(resultado, new TypeReference<>() {});
        assertTrue(listaProfesores.size() > 0);
        ProfesotOutputDto profesorObtenido = listaProfesores.get(0);
        ProfesotOutputDto profesorEsperado = IProfesorMapper.mapper.profesorToProfesorOutputDto(datosGuardadosUno.getFirst());
        assertEquals(profesorObtenido.getBranch(), profesorEsperado.getBranch());
    }

    @Test
    @DisplayName("Modificando los datos de profesor por su id")
    void updateProfesorById() throws Exception {
        Pair<Profesor, Persona> datosGuardados = crearProfesor();

        Persona persona = crearPersona();
        ProfesorInputDto request = new ProfesorInputDto("Comentario modificado", "Back", persona.getId());

        this.mockMvc.perform(put("/profesor/{idProf}", datosGuardados.getFirst().getIdProfesor())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk()).andReturn();

        Profesor profesorObtenido = profesorRepository.findById(datosGuardados.getFirst().getIdProfesor()).get();
        assertEquals(datosGuardados.getFirst().getComents(), profesorObtenido.getComents());
    }

    @Test
    @DisplayName("Borrando datos de profesor por su id ")
    void deleteProfesorById() throws Exception {
        Pair<Profesor, Persona> datosGuardados = crearProfesor();

        mockMvc.perform(delete("/profesor/{id}", datosGuardados.getFirst().getIdProfesor()))
                .andExpect(status().isOk());

        assertThat(profesorRepository.findAll()).isEmpty();
    }
}