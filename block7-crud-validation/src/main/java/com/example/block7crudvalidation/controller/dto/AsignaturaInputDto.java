package com.example.block7crudvalidation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaInputDto {
    String id;

    String asignatura;

    String comments;

    Date initialDate;

    Date finishDate;

    String idStudent;
}
