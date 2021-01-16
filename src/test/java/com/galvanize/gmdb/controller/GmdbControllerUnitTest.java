package com.galvanize.gmdb.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.gmdb.model.MovieEntity;
import com.galvanize.gmdb.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GmdbControllerUnitTest {

    @Mock
    MovieService movieService;

    @Autowired
    ObjectMapper objectMapper;

    ArrayList<MovieEntity> expectedMovieList;
    String movieJsonPath = "src/test/data/movies.json";

    String singleMovieJsonPath = "src/test/data/movie.json";

    @BeforeEach
    public void setUp() throws IOException {
        initalizeMovieData();
    }

    /**
     * As a user, I should see a list of movies when I visit GMDB.
     * <p>
     * When I visit GMDB
     * Then I can see a list of all movies.
     */

    @Test
    public void getAllMovies() throws Exception {

        when(movieService.getAllMovies()).thenReturn(expectedMovieList);
        GmdbController gmdbController = new GmdbController(movieService);
        List<MovieEntity> actualMovieList = gmdbController.getAllMovies();
        assertEquals(expectedMovieList, actualMovieList);
    }

    @Test
    public void getAMovieByTitle() throws Exception {
        MovieEntity expectedMovie = initalizeSingleData();
        when(movieService.getAMovieByTitle("The Avengers")).thenReturn(expectedMovie);
        GmdbController gmdbController = new GmdbController(movieService);
        ResponseEntity<Object> actualMovie = gmdbController.getAMovieByTitle("The Avengers");
        assertEquals(expectedMovie, actualMovie.getBody());
    }

    private void initalizeMovieData() throws IOException {
        objectMapper = new ObjectMapper();
        File movieFile = new File(movieJsonPath);
        expectedMovieList = objectMapper.readValue(movieFile, new TypeReference<ArrayList<MovieEntity>>() {
        });
    }

    private MovieEntity initalizeSingleData() throws IOException {
        objectMapper = new ObjectMapper();
        File movieFile = new File(singleMovieJsonPath);
        MovieEntity movie = objectMapper.readValue(movieFile, new TypeReference<MovieEntity>() {
        });
        return movie;
    }


}