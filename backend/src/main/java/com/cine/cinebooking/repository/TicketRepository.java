package com.cine.cinebooking.repository;

import com.cine.cinebooking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
