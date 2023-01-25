package com.tripbackend.repository;

import com.tripbackend.domain.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IViajeRepository extends JpaRepository<Viaje, Integer> {
}
