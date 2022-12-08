package com.example.examen_JPA_cascada.repository;

import com.example.examen_JPA_cascada.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
}
