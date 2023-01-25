package com.tripbackend.mapper;

import com.tripbackend.controller.dto.ViajeInputDto;
import com.tripbackend.controller.dto.ViajeOutputDto;
import com.tripbackend.domain.Viaje;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IViajeMapper {
    IViajeMapper mapper = Mappers.getMapper(IViajeMapper.class);

    Viaje viajeInputDtoToViaje(ViajeInputDto viajeInput);
    ViajeOutputDto viajeToViajeOutputDto(Viaje viaje);
}
