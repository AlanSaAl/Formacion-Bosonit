package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentFullOutputDto extends StudentOutputDto{
    PersonaOutputDto persona;
}
