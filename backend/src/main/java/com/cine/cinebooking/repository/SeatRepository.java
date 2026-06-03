package com.cine.cinebooking.repository;

import com.cine.cinebooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByRoomIdOrderByRowAscNumberAsc(Long roomId);
}
