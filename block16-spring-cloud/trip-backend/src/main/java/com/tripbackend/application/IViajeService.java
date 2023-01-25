package com.tripbackend.application;

import com.tripbackend.controller.dto.ViajeInputDto;
import com.tripbackend.controller.dto.ViajeOutputDto;

public interface IViajeService {
    ViajeOutputDto addViaje(ViajeInputDto viajeInput);
    ViajeOutputDto addPasajero(int idViaje, int idPasajero);
    Iterable<ViajeOutputDto> getAllViajes(int pageNumber, int pageSize);
    ViajeOutputDto getViajeById(int idViaje);
    ViajeOutputDto modifyViajeById(int idViaje, ViajeInputDto viajeInput);
    void deleteViajeById(int idViaje);
}
