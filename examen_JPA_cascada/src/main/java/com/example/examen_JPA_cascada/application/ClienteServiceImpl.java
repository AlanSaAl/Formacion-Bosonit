package com.example.examen_JPA_cascada.application;

import com.example.examen_JPA_cascada.domain.Cliente;
import com.example.examen_JPA_cascada.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService{
    @Autowired
    IClienteRepository clienteRepository;

    @Override
    public Cliente addCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente getClienteById(int id) {
        return clienteRepository.findById(id).orElseThrow();
    }
}
