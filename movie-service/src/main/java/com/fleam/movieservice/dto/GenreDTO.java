package com.fleam.movieservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public class GenreDTO {
    public static final int NUM_OF_MOVIES = 10;
    public Long id;
    public String name;
    public List<MovieDTO> movies;


    public void setMovies(List<MovieDTO> movies){
        // sets given movies randomly
        Random rand = new Random();
        int max_range = rand.nextInt(movies.size());
        int min_range = Math.max(0, max_range-NUM_OF_MOVIES);
        this.movies = movies.subList(min_range, max_range);
    }

    public void setMoviesNotRandom(List<MovieDTO> movies){
        this.movies = movies;
    }


}