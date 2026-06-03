package com.cine.cinebooking.dto;

public record SeatResponse(
        Long id,
        String row,
        Integer number,
        boolean available
) {
}
