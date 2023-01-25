package com.tripfrontend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private int idTicket;

    private int passangerId;

    private String passangerName;

    private String passangerLastname;

    private String passangerEmail;

    private String tripOrigin;

    private String tripDestination;

    private LocalDate departureDate;

    private LocalDate arraivalDate;
}
