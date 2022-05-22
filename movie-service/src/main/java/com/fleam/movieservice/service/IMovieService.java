package com.fleam.movieservice.service;

import com.fleam.movieservice.dto.MovieDTO;
import com.fleam.movieservice.dto.MovieDetailsDTO;
import com.fleam.movieservice.dto.MovieForm;
import com.fleam.movieservice.entity.Movie;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface IMovieService {
    public Movie createMovie(MovieForm movieForm);

    public MovieDetailsDTO getMovieDetails(long id, String authHeader);

    public MovieDTO getMovie(long id);


    public ResponseEntity<byte[]> streamMovie(long id, String rangeList) throws IOException;

    public byte[] readPartOfMovie(Movie movie, long[] range) throws IOException;

    public String getMoviePath(Movie movie);

    public long getSizeOfMovie(Movie movie);

}
