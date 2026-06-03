package com.cine.cinebooking.controller;

import com.cine.cinebooking.dto.SeatResponse;
import com.cine.cinebooking.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final SeatService seatService;

    public SessionController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/{sessionId}/seats")
    public List<SeatResponse> getSessionSeats(@PathVariable Long sessionId) {
        return seatService.findBySession(sessionId);
    }
}
