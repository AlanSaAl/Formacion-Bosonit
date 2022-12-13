package com.example.block7crudvalidation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesotOutputDto {
    String idProfesor;

    String coments;

    String branch;

    List<StudentOutputDto> students;
}
