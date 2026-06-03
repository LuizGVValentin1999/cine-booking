package com.cine.cinebooking.repository;

import com.cine.cinebooking.entity.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {

    List<MovieSession> findByMovieIdOrderByStartTimeAsc(Long movieId);
}
