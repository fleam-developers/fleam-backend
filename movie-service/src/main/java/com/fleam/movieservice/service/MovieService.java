package com.fleam.movieservice.service;

import com.fleam.movieservice.client.AccountServiceClient;
import com.fleam.movieservice.client.RecommendationServiceClient;
import com.fleam.movieservice.dto.MovieDTO;
import com.fleam.movieservice.dto.MovieDetailsDTO;
import com.fleam.movieservice.dto.CreateMovieForm;
import com.fleam.movieservice.entity.Movie;
import com.fleam.movieservice.mapper.Mapper;
import com.fleam.movieservice.repository.MovieRepository;
import com.fleam.movieservice.util.ServiceUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

    @Autowired
    private RecommendationServiceClient recommendationServiceClient;

    @Override
    public Movie createMovie(CreateMovieForm movieForm){
        Long id = movieRepository.maxId()+1;
        Movie movie = new Movie(id, movieForm.name, movieForm.description, movieForm.poster_url, movieForm.creator_id, null);
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public boolean uploadMovie(long movieId, MultipartFile multipartFile){
        File file = new File(movies_path+"/"+ movieId +".mp4");
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public MovieDetailsDTO getMovieDetails(long id, String authHeader) {
        MovieDetailsDTO movie = mapper.objectToDTO(movieRepository.getById(id), MovieDetailsDTO.class);
        movie.setComments(accountServiceClient.getCommentsOfMovie(authHeader, id));
        movie.setAverageRating(accountServiceClient.getAverageRatingForMovie(authHeader, id));
        movie.setSimilar_movies(getSimilarMovies(id, authHeader));
        return movie;
    }

    @Override
    public MovieDTO getMovie(long movieId){
        return mapper.objectToDTO(movieRepository.getById(movieId), MovieDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> streamMovie(long id, String rangeList) throws IOException {
        long[] range = ServiceUtility.parseHttpRangeHeader(rangeList);
        Movie movie = movieRepository.getById(id);

        long contentSize = getSizeOfMovie(movie);

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

//        if (range[0] <= 20){
//            System.out.println("first requests, create watching at account service");
//            System.out.println(authHeader);
//            accountServiceClient.createWatching(authHeader, movie.getId());
//        }

        HttpHeaders headers = ServiceUtility.httpVideoBaseHeaders(contentSize, range[0], range[1]);
        byte[] data = readPartOfMovie(movie, range);
        return ResponseEntity.status(status).headers(headers).body(data);
    }

    @Override
    public byte[] readPartOfMovie(Movie movie, long[] range) throws IOException {
        byte[] data = Files.readAllBytes(Path.of(getMoviePath(movie))); // FileUtils.readFileToByteArray(movie.getContentPath().getFile());
        byte[] result = new byte[(int) (range[1] - range[0]) + 1];
        System.arraycopy(data, (int) range[0], result, 0, (int) (range[1] - range[0]) + 1);
        return result;
    }

    public String getMoviePath(Movie movie){
        if (movie.getCreator_id() == null){
            return movies_path +"/1.mp4"; // default video
        }
        else{
            return movies_path +"/"+movie.getId()+".mp4";
        }
    }

    public long getSizeOfMovie(Movie movie){
            File file = new File(getMoviePath(movie));
            return file.length();
    }

    @Override
    public List<MovieDTO> getSimilarMovies(long movieId, String authHeader){
        List<Long> similarMovieIds = recommendationServiceClient.getRecommendationsForMovie(movieId, authHeader);
        List<MovieDTO> movies = mapper.objectsToDTOs(movieRepository.findAllById(similarMovieIds), MovieDTO.class);
        return movies;
    }

    @Override
    public List<Movie> searchMovieByName(String name){
        return movieRepository.findByNameContainingIgnoreCase(name);
    }

}
