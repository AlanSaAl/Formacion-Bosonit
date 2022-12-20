package com.example.block11uploaddownloadfiles.application;

import com.example.block11uploaddownloadfiles.controller.dto.DatosFicheroInputDto;
import com.example.block11uploaddownloadfiles.controller.dto.DatosFicheroOutputDto;
import com.example.block11uploaddownloadfiles.domain.DatosFichero;
import com.example.block11uploaddownloadfiles.exceptions.EntityNotFoundException;
import com.example.block11uploaddownloadfiles.mapper.DatosFicheroMapper;
import com.example.block11uploaddownloadfiles.repository.DatosFicheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DatosFicheroServiceImpl implements DatosFicheroService {
    @Autowired
    DatosFicheroRepository datosFicheroRepository;

    @Override
    public DatosFicheroOutputDto getDatosFicheroById(int id) {
        try {
            DatosFichero datosFichero = datosFicheroRepository.findById(id).orElseThrow();
            return DatosFicheroMapper.mapper.datosFicheroToDatosFicheroOutputDto(datosFichero);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id: " + id);
        }
    }

    @Override
    public DatosFicheroOutputDto addMetadatos(DatosFicheroInputDto datosFicheroInputDto) {
        DatosFichero datosFichero = DatosFicheroMapper.mapper.datosFicheroInputDtoToDatosFichero(datosFicheroInputDto);
        datosFicheroRepository.save(datosFichero);
        return DatosFicheroMapper.mapper.datosFicheroToDatosFicheroOutputDto(datosFichero);
    }
}
