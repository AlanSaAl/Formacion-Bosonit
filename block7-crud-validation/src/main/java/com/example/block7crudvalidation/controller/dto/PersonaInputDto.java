package com.example.block7crudvalidation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaInputDto {
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
