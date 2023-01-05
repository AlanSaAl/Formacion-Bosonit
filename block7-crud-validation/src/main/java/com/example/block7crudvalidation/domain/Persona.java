package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.PersonaFullOutputDto;
import com.example.block7crudvalidation.controller.dto.ProfesotOutputDto;
import com.example.block7crudvalidation.mapper.IStudentMapper;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Builder
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    private int id;

    private String usuario;

    private String password;

    private String name;

    private String surname;

    private String company_email;

    private String personal_email;

    private String city;

    private boolean active;

    private LocalDate created_date;

    private String imagen_url;

    private LocalDate termination_date;

    @OneToOne(cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    private Profesor profesor;

    public PersonaFullOutputDto personaToPersonaFullOutputDto() {
        ProfesotOutputDto profesotOutputDto = Objects.equals(this.profesor, null) ? null : this.profesor.profesorToProfesorOutputDto();

        PersonaFullOutputDto personaFullOutputDto = new PersonaFullOutputDto(IStudentMapper.mapper.studentToStudentOutputDto(this.student), profesotOutputDto);

        personaFullOutputDto.setId(this.id);
        personaFullOutputDto.setUsuario(this.usuario);
        personaFullOutputDto.setPassword(this.password);
        personaFullOutputDto.setName(this.name);
        personaFullOutputDto.setSurname(this.surname);
        personaFullOutputDto.setCompany_email(this.company_email);
        personaFullOutputDto.setPersonal_email(this.personal_email);
        personaFullOutputDto.setCity(this.city);
        personaFullOutputDto.setActive(this.active);
        personaFullOutputDto.setCreated_date(this.created_date);
        personaFullOutputDto.setImagen_url(this.imagen_url);
        personaFullOutputDto.setTermination_date(this.termination_date);
        return personaFullOutputDto;
    }
}
