package com.galvanize.gmdb.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;
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
    double rating;
    String title;
}
