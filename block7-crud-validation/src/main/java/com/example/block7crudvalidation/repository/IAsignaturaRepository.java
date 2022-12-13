package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.domain.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAsignaturaRepository extends JpaRepository<Asignatura,String> {
    List<Asignatura> findByStudent_idStudent(String id);
}
