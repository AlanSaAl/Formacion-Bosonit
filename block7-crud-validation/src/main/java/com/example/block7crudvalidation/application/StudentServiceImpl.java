package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.domain.Asignatura;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.IAsignaturaRepository;
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
    @Autowired
    IAsignaturaRepository asignaturaRepository;

    public void validarDatosStudent(Student student) {
        try {
            Objects.requireNonNull(student.getNumHoursWeek(), "horas_por_semana no puede ser nulo");
            Objects.requireNonNull(student.getBranch(), "rama no puede ser nulo");
        } catch (NullPointerException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
    }

    @Override
    public Student addStudent(Student student, int idPersona, String idProfesor) {
        validarDatosStudent(student);

        Persona persona = personaService.getPersonaById(idPersona);
        if (persona.getStudent() != null || persona.getProfesor() != null)
            throw new UnprocessableEntityException("La persona: " + idPersona + " ya tiene datos de estudiante o profesor asignados.");

        Profesor profesor = profesorService.getProfesorById(idProfesor);

        persona.setStudent(student);
        student.setPersona(persona);
        student.setProfesor(profesor);
        List<Student> listaStudents = profesor.getStudentList();
        listaStudents.add(student);
        profesor.setStudentList(listaStudents);

        profesorService.updateProfesorById(idProfesor, profesor);

        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(String id) {
        try {
            return studentRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            throw new EntityNotFoundException("No se encontró el id: " + id);
        }
    }

    @Override
    public List<StudentOutputDto> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest).stream().map(Student::studentToStudentOutputDto).toList();

    }

    @Override
    public Student updateStudentById(String idStudent, Student student) {
        validarDatosStudent(student);
        getStudentById(idStudent);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(String id) {
        getStudentById(id);
        List<Asignatura> asignaturas = asignaturaRepository.findByStudent_idStudent(id);
        asignaturas.forEach(asignatura -> asignaturaRepository.deleteById(asignatura.getIdAsignatura()));
        studentRepository.deleteById(id);
    }
}
