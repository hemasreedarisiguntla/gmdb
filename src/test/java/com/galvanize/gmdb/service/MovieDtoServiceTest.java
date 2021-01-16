package com.galvanize.gmdb.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.gmdb.model.MovieDto;
import com.galvanize.gmdb.model.MovieEntity;
import com.galvanize.gmdb.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
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


    @BeforeEach
    public void setUp() throws IOException {
        initalizeMovieData();
    }

    @Test
    public void getAllMovies() {
        when(movieRepository.findAll()).thenReturn(movieDtoList);
        assertEquals(movieService.getAllMovies(), movieDtoList);
    }

    private void initalizeMovieData() throws IOException {
        objectMapper = new ObjectMapper();
        File movieFile = new File(movieJsonPath);
            movieDtoList = objectMapper.readValue(movieFile, new TypeReference<ArrayList<MovieEntity>>() {
        });
    }

}