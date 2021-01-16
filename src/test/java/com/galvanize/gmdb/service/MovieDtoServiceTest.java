package com.galvanize.gmdb.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.gmdb.exception.MovieNotFoundException;
import com.galvanize.gmdb.model.MovieEntity;
import com.galvanize.gmdb.model.Rating;
import com.galvanize.gmdb.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieDtoServiceTest {

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieService movieService;

    @Autowired
    ObjectMapper objectMapper;

    ArrayList<MovieEntity> movieDtoList;
    String movieJsonPath = "src/test/data/movies.json";
    String singleMovieJsonPath = "src/test/data/movie.json";


    @BeforeEach
    public void setUp() throws IOException {
        initalizeMovieData();
    }

    @Test
    public void getAllMovies() {
        when(movieRepository.findAll()).thenReturn(movieDtoList);
        assertEquals(movieService.getAllMovies(), movieDtoList);
    }

    @Test
    public void getAllMoviesForZero() {
        when(movieRepository.findAll()).thenReturn(null);
        assertEquals(movieService.getAllMovies(), null);
    }

    @Test
    public void getAllMoviesForOne() throws IOException {
        initalizeSingleData();
        when(movieRepository.findAll()).thenReturn(movieDtoList);
        assertEquals(movieService.getAllMovies(), movieDtoList);
    }

    @Test
    public void getAMovieByTitle() throws IOException, MovieNotFoundException {
        MovieEntity movie = initalizeSingleData();
        when(movieRepository.findByTitle("The Avengers")).thenReturn(movie);
        assertEquals(movie,movieService.getAMovieByTitle("The Avengers"));
    }

    @Test
    public void getAMovieByTitleNotFound() throws IOException {

        when(movieRepository.findByTitle(Mockito.anyString())).thenReturn(null);
        MovieNotFoundException expected = assertThrows(MovieNotFoundException.class,
                () ->movieService.getAMovieByTitle("abc"));
        assertEquals("Movie not found", expected.getMessage());
    }

    @Test
    public void addRatingToExistingMovie() throws IOException, MovieNotFoundException {
        MovieEntity movieEntityExpected = initalizeSingleData();
        movieEntityExpected.setRating(5.0);
        when(movieRepository.findByTitle("The Avengers")).thenReturn(movieEntityExpected);
        movieService.addRating(movieEntityExpected.getTitle(), 5.0);
        Mockito.verify(movieRepository).save(movieEntityExpected);
    }

    @Test
    public void addTwoRatingToExistingMovie() throws IOException, MovieNotFoundException {
        MovieEntity movieEntityExpected = initalizeSingleData();
        when(movieRepository.findByTitle("The Avengers")).thenReturn(movieEntityExpected);

        movieService.addRating(movieEntityExpected.getTitle(), 5.0);
        MovieEntity actualMovie = movieService.addRating(movieEntityExpected.getTitle(), 3.0);

        assertEquals(4.0, actualMovie.getRating());
        Mockito.verify(movieRepository,times(2)).save(movieEntityExpected);
    }

    private void initalizeMovieData() throws IOException {
        objectMapper = new ObjectMapper();
        File movieFile = new File(movieJsonPath);
            movieDtoList = objectMapper.readValue(movieFile, new TypeReference<ArrayList<MovieEntity>>() {
        });
    }

    private MovieEntity initalizeSingleData() throws IOException {
        objectMapper = new ObjectMapper();
        File movieFile = new File(singleMovieJsonPath);
        MovieEntity movie = objectMapper.readValue(movieFile, new TypeReference<MovieEntity>(){});
        return movie;
    }



}