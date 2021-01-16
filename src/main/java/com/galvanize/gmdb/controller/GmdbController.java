package com.galvanize.gmdb.controller;

import com.galvanize.gmdb.exception.MovieNotFoundException;
import com.galvanize.gmdb.model.MovieEntity;
import com.galvanize.gmdb.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class GmdbController {

    MovieService movieService;

    public GmdbController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    public List<MovieEntity> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{movieTitle}")
    public ResponseEntity<Object> getAMovieByTitle(@PathVariable String movieTitle) {
        ResponseEntity<Object> responseToBeReturned;
        try {
           responseToBeReturned = new ResponseEntity<Object>(movieService.getAMovieByTitle(movieTitle), HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            responseToBeReturned = new ResponseEntity<Object>("Movie not found.", HttpStatus.NOT_FOUND);
        }
        return responseToBeReturned;
    }

    @PostMapping("/{movieTitle}/reviews/{rating}")
    public MovieEntity addRatingToMovieTitle(@PathVariable String movieTitle, @PathVariable Double rating) throws MovieNotFoundException {
        return movieService.addRating(movieTitle, rating);
    }
}
