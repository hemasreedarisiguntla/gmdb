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
                .andExpect(status().isOk());
//                .andExpect(jsonPath("[0].title").value("The Avengers"))
//                .andExpect(jsonPath("[0].release").value("2012"));

    }

}