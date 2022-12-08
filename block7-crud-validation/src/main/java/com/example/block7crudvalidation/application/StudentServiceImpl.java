package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.mapper.IStudentMapper;
import com.example.block7crudvalidation.repository.IPersonaRepository;
import com.example.block7crudvalidation.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class StudentServiceImpl implements IStudentService{
    @Autowired
    IStudentRepository studentRepository;
    @Autowired
    PersonaServiceImpl personaService;
    @Autowired
    ProfesorServiceImpl profesorService;

    public void validarDatosStudent(StudentInputDto student) {
        try {
            Objects.requireNonNull(student.getNumHoursWeek(), "horas_por_semana no puede ser nulo");
            Objects.requireNonNull(student.getBranch(), "rama no puede ser nulo");
        } catch (NullPointerException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
    }

    @Override
    public Student addStudent(StudentInputDto studentInput) { // falta setear el profesor. traer la persona y el profesor con los metodos de la implementacion
        validarDatosStudent(studentInput);
        Persona persona = personaService.getPersonaById(studentInput.getIdPersona());
        if (persona.getStudent() != null || persona.getProfesor() != null)
            throw new UnprocessableEntityException("La persona: " + studentInput.getIdPersona() + " ya tiene datos de estudiante o profesor asignados.");
        Profesor profesor = profesorService.getProfesorById(studentInput.getIdProfesor());
        Student student = IStudentMapper.mapper.studentInputDtoToStudent(studentInput);
        persona.setStudent(student);
        student.setPersona(persona);
        student.setProfesor(profesor);
        List<Student> listaStudents = profesor.getStudents();
        listaStudents.add(student);
        profesor.setStudents(listaStudents);
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(String id) {
        try {
            Student test = studentRepository.findById(id).orElseThrow();
            return studentRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            throw new EntityNotFoundException("No se encontró el id: " + id);
        }
    }

    @Override
    public List<StudentOutputDto> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest).stream().map(IStudentMapper.mapper::studentToStudentOutputDto).toList();

    }

    @Override
    public Student updateStudentById(StudentInputDto studentInput) { // aqui se usa el idStudent traido desde el input ¿quitar del input el idStudent?
        validarDatosStudent(studentInput);
        getStudentById(studentInput.getIdStudent());
        return studentRepository.save(IStudentMapper.mapper.studentInputDtoToStudent(studentInput));
    }

    @Override
    public void deleteStudentById(String id) {
        try {
            studentRepository.findById(id).orElseThrow();
            studentRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id: " + id);
        }
    }
}
