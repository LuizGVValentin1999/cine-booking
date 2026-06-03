package com.cine.cinebooking.repository;

import com.cine.cinebooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
