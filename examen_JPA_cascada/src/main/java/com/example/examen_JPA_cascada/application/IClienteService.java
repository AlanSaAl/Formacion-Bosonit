package com.example.examen_JPA_cascada.application;

import com.example.examen_JPA_cascada.controller.dto.ClienteInputDto;
import com.example.examen_JPA_cascada.controller.dto.ClienteOutputDto;
import com.example.examen_JPA_cascada.domain.Cliente;

public interface IClienteService {
    Cliente addCliente(ClienteInputDto clienteInput);
    ClienteOutputDto getClienteById(int id);
}
