package com.example.examen_JPA_cascada.mapper;

import com.example.examen_JPA_cascada.controller.dto.FacturaInputDto;
import com.example.examen_JPA_cascada.controller.dto.FacturaOutputDto;
import com.example.examen_JPA_cascada.domain.CabeceraFra;
import com.example.examen_JPA_cascada.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICabeceraFraMapper {
    ICabeceraFraMapper mapper = Mappers.getMapper(ICabeceraFraMapper.class);
    CabeceraFra facturaInputDtoToCabeceraFra(FacturaInputDto facturaInput);
    FacturaOutputDto cabeceraFraToFacturaOutputDto(CabeceraFra cabeceraFra);
}
