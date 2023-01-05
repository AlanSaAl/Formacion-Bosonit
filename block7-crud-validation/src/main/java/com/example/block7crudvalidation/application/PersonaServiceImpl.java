package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.mapper.IPersonaMapper;
import com.example.block7crudvalidation.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.*;

@Service
public class PersonaServiceImpl implements IPersonaService{
    @Autowired
    IPersonaRepository personaRepository;
    @PersistenceContext
    EntityManager entityManager;

    public void validarDatosPersona(PersonaInputDto persona) {
        try {
            Objects.requireNonNull(persona.getUsuario(), "Usuario no puede ser nulo");
            Objects.requireNonNull(persona.getPassword(), "Password no puede ser nulo");
            Objects.requireNonNull(persona.getName(), "Name no puede ser nulo");
            Objects.requireNonNull(persona.getCompany_email(), "company_email no puede ser nulo");
            Objects.requireNonNull(persona.getPersonal_email(), "personal_email no puede ser nulo");
            Objects.requireNonNull(persona.getCity(), "city no puede ser nulo");
            Objects.requireNonNull(persona.getCreated_date(), "created_date no puede ser nulo");
        } catch (NullPointerException e) {
            throw new UnprocessableEntityException(e.getMessage());
        } if (persona.getUsuario().length() > 10) {
            throw new UnprocessableEntityException("Longitud de usuario no puede ser superior a 10 caracteres");
        }
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInput) {
        validarDatosPersona(personaInput);
        Persona persona = IPersonaMapper.mapper.personaInputDtoToPersona(personaInput);
        personaRepository.save(persona);
        return IPersonaMapper.mapper.personaToPersonaOutputDto(persona);
    }

    @Override
    public Persona getPersonaById(int idPersona) {
        try {
            return personaRepository.findById(idPersona).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id: " + idPersona);
        }
    }

    @Override
    public PersonaOutputDto getPersonaByUsuario(String usuario) {
        Persona persona = personaRepository.findByUsuario(usuario);
        return IPersonaMapper.mapper.personaToPersonaOutputDto(persona);
    }

    @Override
    public List<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).stream().map(IPersonaMapper.mapper::personaToPersonaOutputDto).toList();
    }

    @Override
    public PersonaOutputDto modifyPersonaById(int idPersona, PersonaInputDto personaInput) {
        getPersonaById(idPersona);
        personaInput.setId(idPersona);
        return addPersona(personaInput);
    }

    @Override
    public void deletePersona(int idPersona) {
        try {
            personaRepository.findById(idPersona).orElseThrow();
            personaRepository.deleteById(idPersona);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public Iterable<PersonaOutputDto> getPersonaByFields(HashMap<String, Object> conditions, String orderBy, int pageNumber, int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field, value) -> {
            switch (field) {
                case "usuario":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "name":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "surname":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "created_date":
                    predicates.add(cb.greaterThan(root.get(field), (LocalDate) value));
                    break;
            }
        });

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        if (Objects.equals(orderBy,"usuario")) query.orderBy(cb.asc(root.get("usuario")));
        if (Objects.equals(orderBy, "name")) query.orderBy(cb.asc(root.get("name")));

        return entityManager
                .createQuery(query)
                .setMaxResults(pageSize) //tamaño de la pagina
                .setFirstResult(pageNumber) // posicion inicial
                .getResultList()
                .stream()
                .map(IPersonaMapper.mapper::personaToPersonaOutputDto)
                .toList();
    }
}
