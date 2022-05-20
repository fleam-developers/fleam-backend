package com.fleam.movieservice.service;

import com.fleam.movieservice.constants.ServiceConstants;
import com.fleam.movieservice.dto.MovieForm;
import com.fleam.movieservice.entity.Movie;
import com.fleam.movieservice.repository.MovieRepository;
import com.fleam.movieservice.util.ServiceUtility;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie createMovie(MovieForm movieForm){
        Movie movie = new Movie(null, movieForm.name, movieForm.description, movieForm.poster_url, null);
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public Movie getMovie(long id) {
        return movieRepository.getById(id);
    }

    @Override
    public ResponseEntity<byte[]> streamMovie(long id, String rangeList) throws IOException {
        if (rangeList == null){
            System.out.println("initial request");
        }
        long[] range = ServiceUtility.parseHttpRangeHeader(rangeList);
        Movie movie = movieRepository.getById(id);
        long contentSize = movie.getContentSize();
        System.out.println("Content size:" + contentSize);
        HttpStatus status;
        if (range[1] <= contentSize){
            // status 206
            status = HttpStatus.PARTIAL_CONTENT;
        }
        else {
            // final request, status: 200 ok
            range[1] = contentSize - 1;
            status = HttpStatus.OK;
        }
        HttpHeaders headers = ServiceUtility.httpVideoBaseHeaders(contentSize, range[0], range[1]);
        byte[] data = readPartOfMovie(movie, range);
        System.out.println(data.length);
        return ResponseEntity.status(status).headers(headers).body(data);
    }

    @Override
    public byte[] readPartOfMovie(Movie movie, long[] range) throws IOException {
        byte[] data = Files.readAllBytes(movie.getContentPath().getFile().toPath()); // FileUtils.readFileToByteArray(movie.getContentPath().getFile());
        System.out.println(String.valueOf(range[0]) + " " + String.valueOf(range[1]) + "___________");
        byte[] result = new byte[(int) (range[1] - range[0]) + 1];
        System.arraycopy(data, (int) range[0], result, 0, (int) (range[1] - range[0]) + 1);
        return result;
    }
}
