package com.fleam.movieservice.service;

import com.fleam.movieservice.client.AccountServiceClient;
import com.fleam.movieservice.client.RecommendationServiceClient;
import com.fleam.movieservice.dto.CatalogDTO;
import com.fleam.movieservice.dto.GenreDTO;
import com.fleam.movieservice.dto.MovieDTO;
import com.fleam.movieservice.entity.Movie;
import com.fleam.movieservice.mapper.Mapper;
import com.fleam.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class CatalogService implements ICatalogService{

    @Autowired
    private Mapper mapper;

    @Autowired
    private GenreService genreService;

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Autowired
    private RecommendationServiceClient recommendationServiceClient;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public CatalogDTO getGenreCatalog(){
        return new CatalogDTO(mapper.objectsToDTOs(genreService.getAllGenres(), GenreDTO.class));
    }

    @Override
    public CatalogDTO getHistoryCatalog(String authHeader, Long userId) {
        List<Long> history = Arrays.asList(accountServiceClient.getHistoryOfUser(authHeader, userId));
        List<MovieDTO> movies = mapper.objectsToDTOs(movieRepository.findAllById(history), MovieDTO.class);
        GenreDTO genre = new GenreDTO();
        genre.setId(0L);
        genre.setName("Watching History");
        genre.setMoviesNotRandom(movies);
        return new CatalogDTO(List.of(genre));
    }

    @Override
    public CatalogDTO getRecommendationCatalog(String authHeader, Long userId){
        List<Long> recommendedMovieIds = recommendationServiceClient.getRecommendationsForUser(userId, authHeader);
        List<MovieDTO> movies = mapper.objectsToDTOs(movieRepository.findAllById(recommendedMovieIds), MovieDTO.class);
        GenreDTO genre = new GenreDTO();
        genre.setId(0L);
        genre.setName("Recommended Movies Based on Your Ratings");
        genre.setMoviesNotRandom(movies);
        return new CatalogDTO(List.of(genre));
    }

}
