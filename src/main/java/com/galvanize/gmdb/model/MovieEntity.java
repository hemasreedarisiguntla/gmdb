package com.galvanize.gmdb.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Table(name = "movieEntity")
public class MovieEntity {
    String title;
    String director;
    String actors;
    String release;
    String description;
    @JoinColumn(name = "title")
    @OneToMany(mappedBy = "movieEntity", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Transient
    List<Rating> ratingList;
    Double rating;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
