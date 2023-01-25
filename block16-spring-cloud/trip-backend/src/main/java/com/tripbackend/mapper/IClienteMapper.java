package com.tripbackend.mapper;

import com.tripbackend.controller.dto.ClienteInputDto;
import com.tripbackend.controller.dto.ClienteOutputDto;
import com.tripbackend.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IClienteMapper {
    IClienteMapper mapper = Mappers.getMapper(IClienteMapper.class);

    Cliente clienteInputDtoToCliente(ClienteInputDto clienteInput);
    ClienteOutputDto clienteToClienteOutputDto(Cliente cliente);
}
