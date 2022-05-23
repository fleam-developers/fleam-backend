package com.fleam.movieservice.service;

import com.fleam.movieservice.dto.GenreDTO;
import com.fleam.movieservice.entity.Genre;
import com.fleam.movieservice.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService implements IGenreService{

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre createGenre(GenreDTO genreDto){
        Genre genre = new Genre(null, genreDto.name, null);
        genreRepository.save(genre);
        return genre;
    }

    @Override
    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(long genreId){
        return genreRepository.getById(genreId);
    }

}
