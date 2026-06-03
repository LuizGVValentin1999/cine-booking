package com.cine.cinebooking.repository;

import com.cine.cinebooking.entity.Ticket;
import com.cine.cinebooking.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findBySessionIdAndStatusAndExpiresAtAfter(
            Long sessionId, TicketStatus status, LocalDateTime time);

    Optional<Ticket> findBySessionIdAndSeatId(Long sessionId, Long seatId);

    @Modifying
    @Query("""
            UPDATE Ticket t
               SET t.status = com.cine.cinebooking.entity.TicketStatus.EXPIRED
             WHERE t.status = com.cine.cinebooking.entity.TicketStatus.PENDING
               AND t.expiresAt < :now
            """)
    int expirePendingBefore(@Param("now") LocalDateTime now);
}
