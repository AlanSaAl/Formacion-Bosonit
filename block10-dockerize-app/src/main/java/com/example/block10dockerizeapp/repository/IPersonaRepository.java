package com.example.block10dockerizeapp.repository;

import com.example.block10dockerizeapp.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
}
