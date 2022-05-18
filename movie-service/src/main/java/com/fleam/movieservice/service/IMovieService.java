package com.fleam.movieservice.service;

import com.fleam.movieservice.dto.MovieForm;
import com.fleam.movieservice.entity.Movie;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface IMovieService {
    public Movie createMovie(MovieForm movieForm);

    public Movie getMovie(long id);

    public ResponseEntity<byte[]> streamMovie(long id, String rangeList) throws IOException;

    public byte[] readPartOfMovie(Movie movie, long[] range) throws IOException;
}
