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
}
