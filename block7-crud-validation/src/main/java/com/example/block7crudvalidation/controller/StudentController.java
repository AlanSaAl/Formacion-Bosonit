package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.StudentServiceImpl;
import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentFullOutputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.mapper.IStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    @PostMapping
    public ResponseEntity<StudentFullOutputDto> addStudent(@RequestBody StudentInputDto studentInput) {
        Student student = studentService.addStudent(studentInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(IStudentMapper.mapper.studentToStudentFullOutputDto(student));
    }

    @GetMapping("{id}")
    public StudentOutputDto getStudentById(@PathVariable String id, @RequestParam(value = "outputType", defaultValue = "simple") String outputType) {
        return Objects.equals(outputType, "simple") ? IStudentMapper.mapper.studentToStudentOutputDto(studentService.getStudentById(id)) :
                Objects.equals(outputType, "full") ? IStudentMapper.mapper.studentToStudentFullOutputDto(studentService.getStudentById(id)) : null;
    }

    @GetMapping
    public List<StudentOutputDto> getAllStudents(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                 @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return studentService.getAllStudents(pageNumber, pageSize);
    }

    @PutMapping
    public ResponseEntity<StudentOutputDto> updateStudentById(@RequestBody StudentInputDto studentInput) {
        return ResponseEntity.ok().body(IStudentMapper.mapper.studentToStudentOutputDto(studentService.updateStudentById(studentInput)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StudentOutputDto> deleteStudentById(@PathVariable String id) {
        StudentOutputDto student = IStudentMapper.mapper.studentToStudentOutputDto(studentService.getStudentById(id));
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().body(student);
    }
}
