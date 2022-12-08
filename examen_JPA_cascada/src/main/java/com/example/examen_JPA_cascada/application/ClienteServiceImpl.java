package com.example.examen_JPA_cascada.application;

import com.example.examen_JPA_cascada.controller.dto.ClienteInputDto;
import com.example.examen_JPA_cascada.controller.dto.ClienteOutputDto;
import com.example.examen_JPA_cascada.domain.Cliente;
import com.example.examen_JPA_cascada.mapper.IClienteMapper;
import com.example.examen_JPA_cascada.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService{
    @Autowired
    IClienteRepository clienteRepository;

    @Override
    public Cliente addCliente(ClienteInputDto clienteInput) {
        return clienteRepository.save(IClienteMapper.mapper.clienteInputDtoToCliente(clienteInput));
    }

    @Override
    public ClienteOutputDto getClienteById(int id) {
        return IClienteMapper.mapper.clienteToClienteOutputDto(clienteRepository.findById(id).orElseThrow());
    }
}
