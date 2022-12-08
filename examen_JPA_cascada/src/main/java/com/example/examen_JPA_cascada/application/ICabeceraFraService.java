package com.example.examen_JPA_cascada.application;

import com.example.examen_JPA_cascada.controller.dto.FacturaInputDto;
import com.example.examen_JPA_cascada.controller.dto.FacturaOutputDto;
import com.example.examen_JPA_cascada.domain.CabeceraFra;

import java.util.List;

public interface ICabeceraFraService {
    //CabeceraFra addCabeceraFra(int idCliente, FacturaInputDto cabeceraFraInput);
    CabeceraFra addCabeceraFra(FacturaInputDto cabeceraFraInput);
    List<FacturaOutputDto> getAllFacturas();
    void deleteFacturaById(int id);
    CabeceraFra getFacturaById(int id);
}
