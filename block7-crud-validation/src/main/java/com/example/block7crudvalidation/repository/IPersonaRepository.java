package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
    Persona findByUsuario(String usuario);
}
