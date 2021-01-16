package com.galvanize.gmdb.model;


import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    Double rating;
    @ManyToOne
    @JoinColumn(name = "movieEntity", nullable = false)
    MovieEntity movieEntity;
}
