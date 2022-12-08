package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.domain.Student;

import java.util.List;

public interface IStudentService {
    Student addStudent(StudentInputDto student);
    Student getStudentById(String id);
    List<StudentOutputDto> getAllStudents(int pageNumber, int pageSize);
    Student updateStudentById(StudentInputDto student);
    void deleteStudentById(String id);
}
