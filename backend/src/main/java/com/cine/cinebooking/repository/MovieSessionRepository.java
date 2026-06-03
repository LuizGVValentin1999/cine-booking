package com.cine.cinebooking.repository;

import com.cine.cinebooking.entity.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {
}
