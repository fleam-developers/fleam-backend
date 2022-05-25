package com.fleam.movieservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {
    public Long id;
    public String name;
    public String poster_url;
    public String description;
    public Long creator_id;
}
