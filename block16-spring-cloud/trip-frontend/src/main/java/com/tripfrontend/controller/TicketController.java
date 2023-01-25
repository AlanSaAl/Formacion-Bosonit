package com.tripfrontend.controller;

import com.tripfrontend.application.ITicketService;
import com.tripfrontend.controller.dto.ClienteOutputDto;
import com.tripfrontend.controller.dto.TicketInputDto;
import com.tripfrontend.controller.dto.TicketOutputDto;
import com.tripfrontend.controller.dto.ViajeOutputDto;
import com.tripfrontend.feign.ITripBackendFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    @Autowired
    ITicketService ticketService;
    @Autowired
    ITripBackendFeign tripBackendFeign;

    @PostMapping("generateTicket/{userId}/{tripId}")
    public ResponseEntity<TicketOutputDto> generateTicket(@PathVariable int userId, @PathVariable int tripId) {
        ClienteOutputDto cliente = tripBackendFeign.getClienteById(userId);
        ViajeOutputDto viaje = tripBackendFeign.getViajeById(tripId);
        TicketInputDto ticketInput = new TicketInputDto();

        ticketInput.setPassangerId(cliente.getIdCliente());
        ticketInput.setPassangerName(cliente.getNombre());
        ticketInput.setPassangerLastname(cliente.getApellido());
        ticketInput.setPassangerEmail(cliente.getEmail());

        ticketInput.setTripOrigin(viaje.getOrigen());
        ticketInput.setTripDestination(viaje.getDestino());
        ticketInput.setDepartureDate(viaje.getFechaPartida());
        ticketInput.setArraivalDate(viaje.getFechaLlegada());

        return ResponseEntity.ok().body(ticketService.addTicket(ticketInput));
    }
}
