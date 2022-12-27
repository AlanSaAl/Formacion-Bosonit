package com.example.block13mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Persona {
    @Id
    private String id;

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
