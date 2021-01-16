package com.galvanize.gmdb.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.gmdb.exception.MovieNotFoundException;
import com.galvanize.gmdb.model.MovieDto;
import com.galvanize.gmdb.model.MovieEntity;
import com.galvanize.gmdb.model.Rating;
import com.galvanize.gmdb.repository.MovieRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    MovieRepository movieRepository;
    ObjectMapper objectMapper;

    ArrayList<MovieEntity> movieDtoList;
    String movieJsonPath = "src/test/data/movies.json";

    public MovieService(MovieRepository movieRepository) throws IOException {
        this.movieRepository = movieRepository;
        seedData();
    }

    public void seedData() throws IOException {
        objectMapper = new ObjectMapper();
        File movieFile = new File(movieJsonPath);
        movieDtoList = objectMapper.readValue(movieFile, new TypeReference<ArrayList<MovieEntity>>() {
        });
        movieRepository.saveAll(movieDtoList);
    }

    public List<MovieEntity> getAllMovies() {
        return movieRepository.findAll();
    }

    public MovieEntity getAMovieByTitle(String movieTitle) throws MovieNotFoundException {
        MovieEntity movie = movieRepository.findByTitle(movieTitle);
        if (movie == null) {
            throw new MovieNotFoundException("Movie not found");
        } else {
            return movie;
        }

    }

    public MovieEntity addRating(String movieTitle, Double rating) throws MovieNotFoundException {
        MovieEntity movieEntity = getAMovieByTitle(movieTitle);
        Rating existingRating = movieEntity.getRating();
        if(existingRating ==null) {
            existingRating = new Rating();
        }
        List<Double> ratings = existingRating.getRatings();
        if(ratings == null) {
            ratings = new ArrayList<>();
        }

        ratings.add(rating);

        existingRating.setOverAllRating(ratings.stream().mapToDouble(val -> val).average().orElse(0.0));
        existingRating.setRatings(ratings);
        movieEntity.setRating(existingRating);

        movieRepository.save(movieEntity);
        return movieEntity;
    }
}
