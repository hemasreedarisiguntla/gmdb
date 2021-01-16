package com.galvanize.gmdb.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.gmdb.exception.MovieNotFoundException;
import com.galvanize.gmdb.model.MovieEntity;
import com.galvanize.gmdb.model.Rating;
import com.galvanize.gmdb.repository.MovieRepository;
import com.galvanize.gmdb.repository.RatingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    MovieRepository movieRepository;
    RatingRepository ratingRepository;
    ObjectMapper objectMapper;

    ArrayList<MovieEntity> movieDtoList;
    String movieJsonPath = "src/test/data/movies.json";

    public MovieService(MovieRepository movieRepository, RatingRepository ratingRepository) throws IOException {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
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

    @Transactional
    public MovieEntity addRating(String movieTitle, Double rating) throws MovieNotFoundException {
        MovieEntity movieEntity = getAMovieByTitle(movieTitle);
        List<Rating> existingRating = movieEntity.getRatingList();
        if (existingRating == null) {
            existingRating = new ArrayList<>();
        }
        Rating newRating = Rating.builder().rating(rating).build();
        existingRating.add(newRating);

        List<Double> ratingList = new ArrayList<>();
        for (Rating item : existingRating) {
            ratingList.add(item.getRating());
        }
        Double overallRating = ratingList.stream().mapToDouble(value -> value).average().orElse(0.0);
        movieEntity.setRatingList(existingRating);
        movieEntity.setRating(overallRating);
//        existingRating.setRating(ratings.stream().mapToDouble(val -> val).average().orElse(0.0));
//        existingRating.setRatings(ratings);
//        movieEntity.setRating(existingRating);
        //ratingRepository.save(newRating);
        //      movieRepository.save(movieEntity);
        movieRepository.save(movieEntity);
        return movieEntity;
    }
}
