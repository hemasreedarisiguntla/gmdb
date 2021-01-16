package com.galvanize.gmdb.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.persistence.Entity;

@Value
public class MovieDto {
    String title;
    String director;
    String actors;
    String release;
    String description;
    String rating;
}