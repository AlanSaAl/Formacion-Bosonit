package com.example.block7crud.repository;

import com.example.block7crud.domain.Persona;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IPersonaRepository extends JpaRepository<Persona, Integer>, PagingAndSortingRepository<Persona, Integer> {
    List<Persona> findAllByNombre(String nombre, PageRequest pageRequest);
}
