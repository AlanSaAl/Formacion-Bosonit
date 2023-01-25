package com.tripbackend.application;

import com.tripbackend.controller.dto.ClienteInputDto;
import com.tripbackend.controller.dto.ClienteOutputDto;

public interface IClienteService {
    ClienteOutputDto addCliente(ClienteInputDto clienteInput);
    Iterable<ClienteOutputDto> getAllClientes(int pageNumber, int pageSize);
    ClienteOutputDto getClienteById(int idCliente);
    ClienteOutputDto modifyClienteById(int idCliente, ClienteInputDto clienteInput);
    void deleteClienteById(int idCliente);
}
