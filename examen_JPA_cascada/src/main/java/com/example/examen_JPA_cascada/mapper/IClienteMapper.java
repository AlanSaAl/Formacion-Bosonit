package com.example.examen_JPA_cascada.mapper;

import com.example.examen_JPA_cascada.controller.dto.ClienteOutputDto;
import com.example.examen_JPA_cascada.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IClienteMapper {
    IClienteMapper mapper = Mappers.getMapper(IClienteMapper.class);
    ClienteOutputDto clienteToClienteOutputDto(Cliente cliente);
}
