package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.StudentServiceImpl;
import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.mapper.IStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    /*@PostMapping
    public ResponseEntity<StudentFullOutputDto> addStudent(@RequestBody StudentInputDto studentInput) {
        Student student = studentService.addStudent(IStudentMapper.mapper.studentInputDtoToStudent(studentInput),
                                                    studentInput.getIdPersona(),
                                                    studentInput.getIdProfesor());
        return ResponseEntity.status(HttpStatus.CREATED).body(IStudentMapper.mapper.studentToStudentFullOutputDto(student));
    }*/

    @GetMapping("{id}")
    public StudentOutputDto getStudentById(@PathVariable String id, @RequestParam(value = "outputType", defaultValue = "simple") String outputType) {
        return Objects.equals(outputType, "simple") ? studentService.getStudentById(id).studentToStudentOutputDto() :
                Objects.equals(outputType, "full") ? studentService.getStudentById(id).studentToStudentFullOutputDto() : null;
    }

    @GetMapping
    public List<StudentOutputDto> getAllStudents(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                 @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return studentService.getAllStudents(pageNumber, pageSize);
    }

    @PutMapping("{idStudent}")
    public ResponseEntity<StudentOutputDto> updateStudentById(@PathVariable String idStudent, @RequestBody StudentInputDto studentInput) {
        Student student = IStudentMapper.mapper.studentInputDtoToStudent(studentInput);
        return ResponseEntity.ok().body(IStudentMapper.mapper.studentToStudentOutputDto(studentService.updateStudentById(idStudent, student)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StudentOutputDto> deleteStudentById(@PathVariable String id) {
        StudentOutputDto student = IStudentMapper.mapper.studentToStudentOutputDto(studentService.getStudentById(id));
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().body(student);
    }
}
