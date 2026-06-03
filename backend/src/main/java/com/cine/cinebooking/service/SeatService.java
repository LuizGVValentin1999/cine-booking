package com.cine.cinebooking.service;

import com.cine.cinebooking.dto.SeatResponse;
import com.cine.cinebooking.entity.MovieSession;
import com.cine.cinebooking.entity.TicketStatus;
import com.cine.cinebooking.mapper.SeatMapper;
import com.cine.cinebooking.repository.MovieSessionRepository;
import com.cine.cinebooking.repository.SeatRepository;
import com.cine.cinebooking.repository.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeatService {

    private final MovieSessionRepository sessionRepository;
    private final SeatRepository seatRepository;
    private final TicketRepository ticketRepository;
    private final SeatMapper seatMapper;

    public SeatService(MovieSessionRepository sessionRepository,
                       SeatRepository seatRepository,
                       TicketRepository ticketRepository,
                       SeatMapper seatMapper) {
        this.sessionRepository = sessionRepository;
        this.seatRepository = seatRepository;
        this.ticketRepository = ticketRepository;
        this.seatMapper = seatMapper;
    }

    @Transactional(readOnly = true)
    public List<SeatResponse> findBySession(Long sessionId) {
        MovieSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada."));

        Set<Long> occupiedSeatIds = ticketRepository
                .findBySessionIdAndStatusAndExpiresAtAfter(sessionId, TicketStatus.PENDING, LocalDateTime.now())
                .stream()
                .map(ticket -> ticket.getSeat().getId())
                .collect(Collectors.toSet());

        return seatRepository.findByRoomIdOrderByRowAscNumberAsc(session.getRoom().getId())
                .stream()
                .map(seat -> seatMapper.toResponse(seat, !occupiedSeatIds.contains(seat.getId())))
                .toList();
    }
}
