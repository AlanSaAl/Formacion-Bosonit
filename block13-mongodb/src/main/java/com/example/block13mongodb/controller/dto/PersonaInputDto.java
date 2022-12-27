package com.example.block13mongodb.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonaInputDto {
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
}
