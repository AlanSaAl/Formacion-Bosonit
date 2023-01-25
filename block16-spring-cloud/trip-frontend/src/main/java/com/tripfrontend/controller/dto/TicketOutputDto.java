package com.tripfrontend.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TicketOutputDto {
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
