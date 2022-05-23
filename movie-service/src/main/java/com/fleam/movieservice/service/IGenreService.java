package com.fleam.movieservice.service;

import com.fleam.movieservice.dto.GenreDTO;
import com.fleam.movieservice.entity.Genre;

import java.util.List;

public interface IGenreService {
    public Genre createGenre(GenreDTO genreDto);

    public List<Genre> getAllGenres();

    public Genre getGenreById(long genreId);


}
