package com.cine.cinebooking.dto;

import jakarta.validation.constraints.NotNull;

public record TicketRequest(
        @NotNull Long customerId,
        @NotNull Long sessionId,
        @NotNull Long seatId
) {
}
