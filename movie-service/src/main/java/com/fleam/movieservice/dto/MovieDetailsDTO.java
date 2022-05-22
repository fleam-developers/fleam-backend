package com.fleam.movieservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieDetailsDTO {
    public Long id;
    public String name;
    public String poster_url;
    public String description;
    public float averageRating;
    public List<MovieCommentDTO> comments;
}
