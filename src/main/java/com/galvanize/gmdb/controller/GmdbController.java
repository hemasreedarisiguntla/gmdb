package com.galvanize.gmdb.controller;

import com.galvanize.gmdb.exception.MovieNotFoundException;
import com.galvanize.gmdb.model.MovieEntity;
import com.galvanize.gmdb.service.MovieService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GmdbController {

    MovieService movieService;

    public GmdbController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/movies")
    public List<MovieEntity> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movies/{movieTitle}")
    public MovieEntity getAMovieByTitle(@PathVariable String movieTitle) throws MovieNotFoundException {
        return movieService.getAMovieByTitle(movieTitle);
    }
}
