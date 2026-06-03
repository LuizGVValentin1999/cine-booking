package com.cine.cinebooking.controller;

import com.cine.cinebooking.dto.TicketRequest;
import com.cine.cinebooking.dto.TicketResponse;
import com.cine.cinebooking.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse reserve(@Valid @RequestBody TicketRequest request) {
        return ticketService.reserve(request);
    }

    @DeleteMapping("/{ticketId}")
    public TicketResponse cancel(@PathVariable Long ticketId) {
        return ticketService.cancel(ticketId);
    }
}
