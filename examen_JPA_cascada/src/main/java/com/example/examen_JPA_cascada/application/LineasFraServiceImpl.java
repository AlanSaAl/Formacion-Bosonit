package com.example.examen_JPA_cascada.application;

import com.example.examen_JPA_cascada.controller.dto.LineaInputDto;
import com.example.examen_JPA_cascada.controller.dto.LineaOutputDto;
import com.example.examen_JPA_cascada.domain.LineasFra;
import com.example.examen_JPA_cascada.mapper.ILineasFraMapper;
import com.example.examen_JPA_cascada.repository.ILineasFraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineasFraServiceImpl implements ILineasFraService{
    @Autowired
    ILineasFraRepository lineasFraRepository;

    @Override
    public List<LineasFra> addLineaFra(List<LineaInputDto> listaLineaInput) {
        return lineasFraRepository.saveAll(ILineasFraMapper.mapper.listaLineaInputDtoTolistaLineasFra(listaLineaInput));
    }

    @Override
    public List<LineaOutputDto> getAllLineasByIdFra(int idFra) {
        return lineasFraRepository.findAllByIdFra(idFra).stream().map(ILineasFraMapper.mapper::lineasFraToLineaOutputDto).toList();
    }
}
