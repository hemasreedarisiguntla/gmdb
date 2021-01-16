package com.galvanize.gmdb.controller;

import com.galvanize.gmdb.model.MovieEntity;
import com.galvanize.gmdb.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
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
}
