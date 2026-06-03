package com.cine.cinebooking.dto;

public record MovieResponse(
        Long id,
        String name,
        Integer durationMin
) {
}
