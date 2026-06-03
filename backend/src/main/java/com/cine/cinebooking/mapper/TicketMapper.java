package com.cine.cinebooking.mapper;

import com.cine.cinebooking.dto.TicketResponse;
import com.cine.cinebooking.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "sessionId", source = "session.id")
    @Mapping(target = "seatId", source = "seat.id")
    TicketResponse toResponse(Ticket ticket);
}
