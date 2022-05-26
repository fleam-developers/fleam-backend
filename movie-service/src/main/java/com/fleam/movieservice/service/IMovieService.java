package com.fleam.movieservice.service;

import com.fleam.movieservice.dto.MovieDTO;
import com.fleam.movieservice.dto.MovieDetailsDTO;
import com.fleam.movieservice.dto.CreateMovieForm;
import com.fleam.movieservice.entity.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IMovieService {
    public Movie createMovie(CreateMovieForm movieForm);

    public boolean uploadMovie(long movieId, MultipartFile multipartFile);


    public MovieDetailsDTO getMovieDetails(long id, String authHeader);

    public MovieDTO getMovie(long id);

    public ResponseEntity<byte[]> streamMovie(long id, String rangeList, String authHeader) throws IOException;

    public byte[] readPartOfMovie(Movie movie, long[] range) throws IOException;

    public String getMoviePath(Movie movie);

    public long getSizeOfMovie(Movie movie);

    public List<MovieDTO> getSimilarMovies(long movieId, String authHeader);

    public List<Movie> searchMovieByName(String name);


}
