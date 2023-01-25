package com.tripbackend.application;

import com.tripbackend.controller.dto.ClienteInputDto;
import com.tripbackend.controller.dto.ClienteOutputDto;
import com.tripbackend.domain.Cliente;
import com.tripbackend.mapper.IClienteMapper;
import com.tripbackend.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService{
    @Autowired
    IClienteRepository clienteRepository;

    @Override
    public ClienteOutputDto addCliente(ClienteInputDto clienteInput) {
        Cliente cliente = IClienteMapper.mapper.clienteInputDtoToCliente(clienteInput);
        return IClienteMapper.mapper.clienteToClienteOutputDto(clienteRepository.save(cliente));
    }

    @Override
    public Iterable<ClienteOutputDto> getAllClientes(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.findAll().stream().map(IClienteMapper.mapper::clienteToClienteOutputDto).toList();
    }

    @Override
    public ClienteOutputDto getClienteById(int idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow();
        return IClienteMapper.mapper.clienteToClienteOutputDto(cliente);
    }

    @Override
    public ClienteOutputDto modifyClienteById(int idCliente, ClienteInputDto clienteInput) {
        getClienteById(idCliente);
        clienteInput.setIdCliente(idCliente);
        return addCliente(clienteInput);
    }

    @Override
    public void deleteClienteById(int idCliente) {
        getClienteById(idCliente);
        clienteRepository.deleteById(idCliente);
    }
}
