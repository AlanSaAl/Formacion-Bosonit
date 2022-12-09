package com.example.examen_JPA_cascada.application;

import com.example.examen_JPA_cascada.domain.CabeceraFra;
import com.example.examen_JPA_cascada.domain.LineasFra;

import java.util.List;

public interface ICabeceraFraService {
    CabeceraFra addCabeceraFra(CabeceraFra cabeceraFra, int idCliente);
    List<CabeceraFra> getAllFacturas();
    void deleteFacturaById(int id);
    CabeceraFra getFacturaById(int idFra);
    CabeceraFra addLineaToFactura(LineasFra lineaFra, int idFra);
}
