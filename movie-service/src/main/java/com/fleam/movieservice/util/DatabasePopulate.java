package com.fleam.movieservice.util;

import com.fleam.movieservice.MovieServiceApplication;
import com.fleam.movieservice.constants.ServiceConstants;
import com.fleam.movieservice.entity.Genre;
import com.fleam.movieservice.entity.Movie;
import com.fleam.movieservice.repository.GenreRepository;
import com.fleam.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.*;

@Service
public class DatabasePopulate {

    @Autowired
    public GenreRepository genreRepository;

    @Autowired
    public MovieRepository movieRepository;

    @Value("${populate-database.genres: false}")
    public boolean isPopulateGenres;

    @Value("${populate-database.movies: false}")
    public boolean isPopulateMovies;

    public void populateGenres(){
        String[] genreNames = new String[]{
                "Action",
                "Adventure",
                "Animation",
                "Children",
                "Comedy",
                "Crime",
                "Documentary",
                "Drama",
                "Fantasy",
                "Film-Noir",
                "Horror",
                "Musical",
                "Mystery",
                "Romance",
                "Sci-Fi",
                "Thriller",
                "War",
                "Western",
        };
        ArrayList<Genre> genreObjs = new ArrayList<>();
        for (String genre_name : genreNames) {
            Genre genre = new Genre(null, genre_name, null);
            genreObjs.add(genre);
        }
        genreRepository.saveAll(genreObjs);
    }

    public void populateMovies() throws IOException {
        ArrayList<Movie> movieObjs = new ArrayList<>();
        ClassPathResource path =  new ClassPathResource(ServiceConstants.MOVIES_PATH + "/" + ServiceConstants.MOVIE_DATA);

        File file = new File(getClass().getClassLoader().getResource("/movies/movies.csv").getFile());
        String data = new String(MovieServiceApplication.class.getClassLoader().getResourceAsStream("/movies/movies.csv").readAllBytes());
        for (String line : data.split("\\r?\\n")) {
            // movieId,title,genres,poster_url
            String[] elems = line.split(",");
            Set<Genre> genres = genreRepository.findAllByNameIn(new HashSet<>(Arrays.asList(elems[2].split("\\|"))));
            String poster_url = null;
            if (elems.length == 4){
                poster_url=elems[3];
            }
            Movie movie = new Movie(Long.parseLong(elems[0]), elems[1], "description", poster_url, genres);
            movieObjs.add(movie);

        }

        movieRepository.saveAll(movieObjs);
    }

}
