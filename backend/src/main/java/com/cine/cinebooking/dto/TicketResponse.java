package com.cine.cinebooking.dto;

import com.cine.cinebooking.entity.TicketStatus;

import java.time.LocalDateTime;

public record TicketResponse(
        Long id,
        Long customerId,
        Long sessionId,
        Long seatId,
        TicketStatus status,
        LocalDateTime expiresAt,
        LocalDateTime createdAt
) {
}
