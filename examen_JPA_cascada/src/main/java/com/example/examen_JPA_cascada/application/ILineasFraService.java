package com.example.examen_JPA_cascada.application;

import com.example.examen_JPA_cascada.controller.dto.LineaInputDto;
import com.example.examen_JPA_cascada.domain.LineasFra;

import java.util.List;

public interface ILineasFraService {
    List<LineasFra> addLineaFra(List<LineaInputDto> listaLineaInput);
}
