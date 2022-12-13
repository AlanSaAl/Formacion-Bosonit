package com.example.block7crudvalidation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDto {
    int numHoursWeek;
    String coments;
    String branch;
    int idPersona;
    String idProfesor;
}
