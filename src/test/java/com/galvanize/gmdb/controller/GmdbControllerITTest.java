package com.galvanize.gmdb.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class GmdbControllerITTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void smoke() {

    }

    /**
     * As a user, I should see a list of movies when I visit GMDB.
     *
     * When I visit GMDB
     * Then I can see a list of all movies.
     */
    @Test
    public void getAllMovies() throws Exception {

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].title").value("The Avengers"))
                .andExpect(jsonPath("[0].release").value("2012"));
    }

    /**
     * As a user, I can browse each movie so I can learn all the details.
     *
     * Rule: Movie details include title, director, actors, release year, description and star rating.
     *
     * Given an existing movie
     * When I visit that title
     * Then I can see all the movie details.
     */

    @Test
    public void getAMovieByTitle() throws Exception {

        mockMvc.perform(get("/movies/{movieTitle}","The Avengers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("The Avengers"))
                .andExpect(jsonPath("release").value("2012"));
    }
    /**
     * Given a non-existing movie
     * When I visit that title
     * Then I receive a friendly message that it doesn't exist.
     */

    @Test
    public void getAMovieByTitleNotFound() throws Exception {
        mockMvc.perform(get("/movies/{movieTitle}", "abc"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Movie not found."));
    }

}