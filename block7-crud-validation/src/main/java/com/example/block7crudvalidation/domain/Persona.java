package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.PersonaFullOutputDto;
import com.example.block7crudvalidation.controller.dto.ProfesotOutputDto;
import com.example.block7crudvalidation.mapper.IStudentMapper;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

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

    private Date created_date;

    private String image_url;

    private Date termination_date;

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
        personaFullOutputDto.setImage_url(this.image_url);
        personaFullOutputDto.setTermination_date(this.termination_date);
        return personaFullOutputDto;
    }
}
