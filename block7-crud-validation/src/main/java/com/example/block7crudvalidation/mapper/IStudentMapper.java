package com.example.block7crudvalidation.mapper;

import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IStudentMapper {
    IStudentMapper mapper = Mappers.getMapper(IStudentMapper.class);
    Student studentInputDtoToStudent(StudentInputDto studentInputDto);
    StudentOutputDto studentToStudentOutputDto(Student student);
    //StudentFullOutputDto studentToStudentFullOutputDto(Student student);
    Student studentOutputDtoToStudent(StudentOutputDto studentOutputDto);
}
