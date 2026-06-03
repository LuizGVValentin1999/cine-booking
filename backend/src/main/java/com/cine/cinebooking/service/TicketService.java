package com.cine.cinebooking.service;

import com.cine.cinebooking.dto.TicketRequest;
import com.cine.cinebooking.dto.TicketResponse;
import com.cine.cinebooking.entity.Customer;
import com.cine.cinebooking.entity.MovieSession;
import com.cine.cinebooking.entity.Seat;
import com.cine.cinebooking.entity.Ticket;
import com.cine.cinebooking.entity.TicketStatus;
import com.cine.cinebooking.mapper.TicketMapper;
import com.cine.cinebooking.repository.CustomerRepository;
import com.cine.cinebooking.repository.MovieSessionRepository;
import com.cine.cinebooking.repository.SeatRepository;
import com.cine.cinebooking.repository.TicketRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class TicketService {

    private static final int RESERVATION_MINUTES = 10;

    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final MovieSessionRepository sessionRepository;
    private final SeatRepository seatRepository;
    private final TicketMapper ticketMapper;

    public TicketService(TicketRepository ticketRepository,
                         CustomerRepository customerRepository,
                         MovieSessionRepository sessionRepository,
                         SeatRepository seatRepository,
                         TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
        this.sessionRepository = sessionRepository;
        this.seatRepository = seatRepository;
        this.ticketMapper = ticketMapper;
    }

    @Transactional
    public TicketResponse reserve(TicketRequest request) {
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
        MovieSession session = sessionRepository.findById(request.sessionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada."));
        Seat seat = seatRepository.findById(request.seatId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assento não encontrado."));

        if (!seat.getRoom().getId().equals(session.getRoom().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Assento não pertence à sala da sessão.");
        }

        LocalDateTime now = LocalDateTime.now();

        // Reaproveita a linha existente (se houver) para respeitar UNIQUE(session_id, seat_id).
        Ticket ticket = ticketRepository.findBySessionIdAndSeatId(session.getId(), seat.getId())
                .orElseGet(Ticket::new);

        boolean active = ticket.getId() != null
                && ticket.getStatus() == TicketStatus.PENDING
                && ticket.getExpiresAt().isAfter(now);
        if (active) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Assento já reservado para esta sessão.");
        }

        ticket.setCustomer(customer);
        ticket.setSession(session);
        ticket.setSeat(seat);
        ticket.setStatus(TicketStatus.PENDING);
        ticket.setCreatedAt(now);
        ticket.setExpiresAt(now.plusMinutes(RESERVATION_MINUTES));

        try {
            return ticketMapper.toResponse(ticketRepository.saveAndFlush(ticket));
        } catch (DataIntegrityViolationException e) {
            // Corrida: outra reserva inseriu o mesmo (session, seat) primeiro.
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Assento já reservado para esta sessão.");
        }
    }

    @Transactional
    public TicketResponse cancel(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada."));
        ticket.setStatus(TicketStatus.CANCELLED);
        return ticketMapper.toResponse(ticketRepository.save(ticket));
    }
}
