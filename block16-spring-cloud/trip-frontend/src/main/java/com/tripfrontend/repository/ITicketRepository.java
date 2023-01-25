package com.tripfrontend.repository;

import com.tripfrontend.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {
}
