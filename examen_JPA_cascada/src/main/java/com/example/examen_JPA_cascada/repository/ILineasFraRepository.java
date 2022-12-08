package com.example.examen_JPA_cascada.repository;

import com.example.examen_JPA_cascada.domain.LineasFra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILineasFraRepository extends JpaRepository<LineasFra, Integer> {
    List<LineasFra> findByIdFra(int id);
}
