package com.cine.cinebooking.repository;

import com.cine.cinebooking.entity.Ticket;
import com.cine.cinebooking.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findBySessionIdAndStatusAndExpiresAtAfter(
            Long sessionId, TicketStatus status, LocalDateTime time);

    Optional<Ticket> findBySessionIdAndSeatId(Long sessionId, Long seatId);
}
