package com.example.block13mongodb.repository;

import com.example.block13mongodb.domain.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPersonaRepository extends MongoRepository<Persona, String> {
}
