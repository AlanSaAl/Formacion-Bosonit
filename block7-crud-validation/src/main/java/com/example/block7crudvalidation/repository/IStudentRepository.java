package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, String> {
}
