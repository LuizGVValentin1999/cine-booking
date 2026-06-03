package com.cine.cinebooking.scheduler;

import com.cine.cinebooking.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TicketExpirationScheduler {

    private static final Logger log = LoggerFactory.getLogger(TicketExpirationScheduler.class);

    private final TicketService ticketService;

    public TicketExpirationScheduler(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Scheduled(fixedRateString = "${app.tickets.expiration.check-rate:10m}")
    public void expireReservations() {
        int expired = ticketService.expireOldReservations();
        if (expired > 0) {
            log.info("Expirou {} reserva(s) PENDING vencida(s).", expired);
        }
    }
}
