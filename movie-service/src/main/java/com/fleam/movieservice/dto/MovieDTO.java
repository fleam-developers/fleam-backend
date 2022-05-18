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
    public String genre;
    public String description;
}