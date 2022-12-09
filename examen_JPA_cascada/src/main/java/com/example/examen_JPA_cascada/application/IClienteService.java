package com.example.examen_JPA_cascada.application;

import com.example.examen_JPA_cascada.domain.Cliente;

public interface IClienteService {
    Cliente addCliente(Cliente cliente);
    Cliente getClienteById(int id);
}
