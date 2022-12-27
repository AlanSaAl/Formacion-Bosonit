package com.example.block13mongodb.application;

import com.example.block13mongodb.controller.dto.PersonaInputDto;
import com.example.block13mongodb.controller.dto.PersonaOutputDto;
import com.example.block13mongodb.domain.Persona;
import com.example.block13mongodb.mapper.IPersonaMapper;
import com.example.block13mongodb.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements IPersonaService{
    @Autowired
    IPersonaRepository personaRepository;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInput){
        Persona persona = IPersonaMapper.mapper.personaInputDtoToPersona(personaInput);
        personaRepository.save(persona);
        return IPersonaMapper.mapper.personaToPersonaOutputDto(persona);
    }

    @Override
    public PersonaOutputDto getPersonaById(String idPersona){
        Persona persona = personaRepository.findById(idPersona).orElseThrow();
        return IPersonaMapper.mapper.personaToPersonaOutputDto(persona);
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).stream().map(IPersonaMapper.mapper::personaToPersonaOutputDto).toList();
    }

    @Override
    public PersonaOutputDto modifyPersonaById(String idPersona, PersonaInputDto personaInput){
        getPersonaById(idPersona);
        Persona persona = IPersonaMapper.mapper.personaInputDtoToPersona(personaInput);
        persona.setId(idPersona);
        personaRepository.save(persona);
        return IPersonaMapper.mapper.personaToPersonaOutputDto(persona);
    }

    @Override
    public void deletePersonaById(String idPersona){
        getPersonaById(idPersona);
        personaRepository.deleteById(idPersona);
    }
}
