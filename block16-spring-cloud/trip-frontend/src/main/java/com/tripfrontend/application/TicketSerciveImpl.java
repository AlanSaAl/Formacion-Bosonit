package com.tripfrontend.application;

import com.tripfrontend.controller.dto.TicketInputDto;
import com.tripfrontend.controller.dto.TicketOutputDto;
import com.tripfrontend.domain.Ticket;
import com.tripfrontend.mapper.ITicketMapper;
import com.tripfrontend.repository.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketSerciveImpl implements ITicketService{
    @Autowired
    ITicketRepository ticketRepository;

    @Override
    public TicketOutputDto addTicket(TicketInputDto ticketInput) {
        Ticket ticket = ITicketMapper.mapper.ticketInputDtoToTicket(ticketInput);
        return ITicketMapper.mapper.ticketToTicketOutputDto(ticketRepository.save(ticket));
    }
}
