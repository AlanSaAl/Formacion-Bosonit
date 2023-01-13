package com.example.block7crudvalidation.controller.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaOutputDto {
    int id;

    String usuario;

    String password;

    String name;

    String surname;

    String company_email;

    String personal_email;

    String city;

    boolean active;

    LocalDate created_date;

    String imagen_url;

    LocalDate termination_date;

    boolean admin;

    // Sobreescritura del método equals para poder comprobar en los test que los valores de dos objetos son iguales
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PersonaOutputDto)) {
            return false;
        }

        PersonaOutputDto objPersona = (PersonaOutputDto) obj;

        return (this == objPersona) || (this.id == objPersona.getId() && this.usuario.equals(objPersona.getUsuario()) && this.password.equals(objPersona.getPassword())
                && this.name.equals(objPersona.getName()) && this.surname.equals(objPersona.getSurname()) && this.company_email.equals(objPersona.getCompany_email())
                && this.personal_email.equals(objPersona.getPersonal_email()) && this.city.equals(objPersona.getCity()) && this.active == objPersona.isActive()
                && this.created_date.equals(objPersona.getCreated_date()) && this.imagen_url.equals(objPersona.getImagen_url()) && this.termination_date.equals(objPersona.getTermination_date()));
    }
}
