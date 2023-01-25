package com.tripfrontend.application;

import com.tripfrontend.controller.dto.TicketInputDto;
import com.tripfrontend.controller.dto.TicketOutputDto;

public interface ITicketService {
    TicketOutputDto addTicket(TicketInputDto ticketInput);
}
