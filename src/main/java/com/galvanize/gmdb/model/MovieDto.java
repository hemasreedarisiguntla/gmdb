package com.galvanize.gmdb.model;

import lombok.Value;

@Value
public class MovieDto {
    String title;
    String director;
    String actors;
    String release;
    String description;
    String rating;
}