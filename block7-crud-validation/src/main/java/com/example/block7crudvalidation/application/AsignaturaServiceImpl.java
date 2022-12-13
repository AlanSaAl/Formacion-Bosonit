package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.AsignaturaInputDto;
import com.example.block7crudvalidation.controller.dto.AsignaturaOutputDto;
import com.example.block7crudvalidation.domain.Asignatura;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.mapper.IAsignaturaMapper;
import com.example.block7crudvalidation.repository.IAsignaturaRepository;
import com.example.block7crudvalidation.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class AsignaturaServiceImpl implements IAsignaturaService{
    @Autowired
    IAsignaturaRepository asignaturaRepository;
    @Autowired
    IStudentRepository studentRepository;
    @Autowired
    IStudentService studentService;

    public void validarDatosAsignatura(AsignaturaInputDto asignaturaInput) {
        try {
            Objects.requireNonNull(asignaturaInput.getInitialDate(), "La fecha inicial no puede ser nulo");
        } catch (NullPointerException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
    }

    @Override
    public Asignatura addAsignatura(AsignaturaInputDto asignaturaInput) {
        validarDatosAsignatura(asignaturaInput);

        try {
            Student student = studentService.getStudentById(asignaturaInput.getIdStudent());
            Asignatura asignatura = IAsignaturaMapper.mapper.asignaturaInputDtoToAsignatura(asignaturaInput);

            asignatura.setStudent(student);
            List<Asignatura> listaAsignaturas = student.getAsignaturaList();
            listaAsignaturas.add(asignatura);
            student.setAsignaturaList(listaAsignaturas);

            studentService.updateStudentById(asignaturaInput.getIdStudent(), student);

            return asignaturaRepository.save(asignatura);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró un estudiante con el id: " + asignaturaInput.getIdStudent());
        }
    }

    @Override
    public Asignatura getAsignaturaById(String id) {
        try {
            return asignaturaRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id: " + id);
        }
    }

    @Override
    public List<AsignaturaOutputDto> getAllAsignaturas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return asignaturaRepository.findAll(pageRequest).stream().map(IAsignaturaMapper.mapper::asignaturaToAsignaturaOutputDto).toList();
    }

    @Override
    public Asignatura updateAsignatura(String id, AsignaturaInputDto asignaturaInput) {
        validarDatosAsignatura(asignaturaInput);
        getAsignaturaById(id);
        return asignaturaRepository.save(IAsignaturaMapper.mapper.asignaturaInputDtoToAsignatura(asignaturaInput));
    }

    @Override
    public void deleteAsignaturaById(String id) {
        getAsignaturaById(id);
        asignaturaRepository.deleteById(id);
    }
}
