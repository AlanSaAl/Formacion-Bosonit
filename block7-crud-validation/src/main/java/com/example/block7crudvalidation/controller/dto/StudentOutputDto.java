package com.example.block7crudvalidation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputDto {
    String idStudent;
    int numHoursWeek;
    String coments;
    String branch;
    List<AsignaturaOutputDto> asignaturas = new ArrayList<>();
}
