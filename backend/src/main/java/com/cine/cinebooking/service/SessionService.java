package com.cine.cinebooking.service;

import com.cine.cinebooking.dto.SessionResponse;
import com.cine.cinebooking.mapper.SessionMapper;
import com.cine.cinebooking.repository.MovieRepository;
import com.cine.cinebooking.repository.MovieSessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SessionService {

    private final MovieSessionRepository sessionRepository;
    private final MovieRepository movieRepository;
    private final SessionMapper sessionMapper;

    public SessionService(MovieSessionRepository sessionRepository,
                          MovieRepository movieRepository,
                          SessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.movieRepository = movieRepository;
        this.sessionMapper = sessionMapper;
    }

    @Transactional(readOnly = true)
    public List<SessionResponse> findByMovie(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado.");
        }
        return sessionRepository.findByMovieIdOrderByStartTimeAsc(movieId)
                .stream()
                .map(sessionMapper::toResponse)
                .toList();
    }
}
