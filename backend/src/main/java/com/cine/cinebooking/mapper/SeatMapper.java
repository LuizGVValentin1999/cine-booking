package com.cine.cinebooking.mapper;

import com.cine.cinebooking.dto.SeatResponse;
import com.cine.cinebooking.entity.Seat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    SeatResponse toResponse(Seat seat, boolean available);
}
