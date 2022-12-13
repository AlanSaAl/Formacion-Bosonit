package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.domain.Student;

import java.util.List;

public interface IStudentService {
    Student addStudent(Student student, int idPersona, String idProfesor);
    Student getStudentById(String id);
    List<StudentOutputDto> getAllStudents(int pageNumber, int pageSize);
    Student updateStudentById(String idStudent, Student student);
    void deleteStudentById(String id);
}
