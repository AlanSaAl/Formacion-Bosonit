package com.example.block7crudvalidation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaOutputDto {
    String idAsignatura;
    String asignatura;
    String comments;
    Date initialDate;
    Date finishDate;
}
