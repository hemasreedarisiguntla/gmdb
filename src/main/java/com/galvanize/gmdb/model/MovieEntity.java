package com.galvanize.gmdb.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String title;
    String director;
    String actors;
    String release;
    String description;
    Double rating;
}
