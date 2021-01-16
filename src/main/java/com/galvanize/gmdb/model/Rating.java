package com.galvanize.gmdb.model;


import lombok.*;

import java.util.List;
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    double overAllRating;
    List<Double> ratings;
}
