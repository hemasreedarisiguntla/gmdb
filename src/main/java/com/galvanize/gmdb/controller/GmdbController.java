package com.galvanize.gmdb.controller;

import com.galvanize.gmdb.exception.MovieNotFoundException;
import com.galvanize.gmdb.model.MovieEntity;
import com.galvanize.gmdb.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getAMovieByTitle(@PathVariable String movieTitle) {
        ResponseEntity<Object> responseToBeReturned;
        try {
           responseToBeReturned = new ResponseEntity<Object>(movieService.getAMovieByTitle(movieTitle), HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            responseToBeReturned = new ResponseEntity<Object>("Movie not found.", HttpStatus.NOT_FOUND);
        }
        return responseToBeReturned;
    }
}
