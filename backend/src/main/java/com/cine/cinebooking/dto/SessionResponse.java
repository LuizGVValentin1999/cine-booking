package com.cine.cinebooking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SessionResponse(
        Long id,
        Long movieId,
        Long roomId,
        String roomName,
        LocalDateTime startTime,
        BigDecimal price
) {
}
