package com.fleam.movieservice.service;

import com.fleam.movieservice.MovieServiceApplication;
import com.fleam.movieservice.client.AccountServiceClient;
import com.fleam.movieservice.constants.ServiceConstants;
import com.fleam.movieservice.dto.MovieDTO;
import com.fleam.movieservice.dto.MovieDetailsDTO;
import com.fleam.movieservice.dto.MovieForm;
import com.fleam.movieservice.entity.Movie;
import com.fleam.movieservice.mapper.Mapper;
import com.fleam.movieservice.repository.MovieRepository;
import com.fleam.movieservice.util.ServiceUtility;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MovieService implements IMovieService {

    @Value("${movies-path: /movies}")
    public String movies_path;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Override
    public Movie createMovie(MovieForm movieForm){
        Movie movie = new Movie(null, movieForm.name, movieForm.description, movieForm.poster_url, null);
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public MovieDetailsDTO getMovieDetails(long id, String authHeader) {
        MovieDetailsDTO movie = mapper.objectToDTO(movieRepository.getById(id), MovieDetailsDTO.class);
        movie.setComments(accountServiceClient.getCommentsOfMovie(authHeader, id));
        movie.setAverageRating(accountServiceClient.getAverageRatingForMovie(authHeader, id));
        return movie;
    }

    @Override
    public MovieDTO getMovie(long movieId){
        return mapper.objectToDTO(movieRepository.getById(movieId), MovieDTO.class);
    }


    @Override
    public ResponseEntity<byte[]> streamMovie(long id, String rangeList) throws IOException {
        if (rangeList == null){
            System.out.println("initial request");
        }
        long[] range = ServiceUtility.parseHttpRangeHeader(rangeList);
        Movie movie = movieRepository.getById(id);

        long contentSize = getSizeOfMovie(movie);

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

        if (range[0] <= 20){
            System.out.println("first requests, create watching at account service");
        }

        HttpHeaders headers = ServiceUtility.httpVideoBaseHeaders(contentSize, range[0], range[1]);
        byte[] data = readPartOfMovie(movie, range);
        System.out.println(data.length);
        return ResponseEntity.status(status).headers(headers).body(data);
    }

    @Override
    public byte[] readPartOfMovie(Movie movie, long[] range) throws IOException {
        byte[] data = Files.readAllBytes(Path.of(getMoviePath(movie))); // FileUtils.readFileToByteArray(movie.getContentPath().getFile());
        System.out.println(String.valueOf(range[0]) + " " + String.valueOf(range[1]) + "___________");
        byte[] result = new byte[(int) (range[1] - range[0]) + 1];
        System.arraycopy(data, (int) range[0], result, 0, (int) (range[1] - range[0]) + 1);
        return result;
    }

    public String getMoviePath(Movie movie){
        return movies_path +"/"+movie.getId()+".mp4";
    }

    public long getSizeOfMovie(Movie movie){
            System.out.println(getMoviePath(movie));
            File file = new File(getMoviePath(movie));
            return file.length();
    }


}
