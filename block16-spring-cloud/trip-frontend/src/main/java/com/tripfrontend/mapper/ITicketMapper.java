package com.tripfrontend.mapper;

import com.tripfrontend.controller.dto.TicketInputDto;
import com.tripfrontend.controller.dto.TicketOutputDto;
import com.tripfrontend.domain.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ITicketMapper {
    ITicketMapper mapper = Mappers.getMapper(ITicketMapper.class);

    Ticket ticketInputDtoToTicket(TicketInputDto ticketInput);
    TicketOutputDto ticketToTicketOutputDto(Ticket ticket);
}
