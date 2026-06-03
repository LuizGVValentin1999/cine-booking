package com.cine.cinebooking.repository;

import com.cine.cinebooking.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
