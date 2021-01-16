package com.galvanize.gmdb.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

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
    @JoinColumn(name="title")
    @OneToMany(cascade = CascadeType.ALL)
    @Transient
    List<Rating> ratingList;

    Double rating;


}
