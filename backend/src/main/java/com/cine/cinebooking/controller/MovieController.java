package com.cine.cinebooking.controller;

import com.cine.cinebooking.dto.MovieResponse;
import com.cine.cinebooking.dto.SessionResponse;
import com.cine.cinebooking.service.MovieService;
import com.cine.cinebooking.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
    private final SessionService sessionService;

    public MovieController(MovieService movieService, SessionService sessionService) {
        this.movieService = movieService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<MovieResponse> getAllMovies() {
        return movieService.findAll();
    }

    @GetMapping("/{movieId}/sessions")
    public List<SessionResponse> getMovieSessions(@PathVariable Long movieId) {
        return sessionService.findByMovie(movieId);
    }
}
