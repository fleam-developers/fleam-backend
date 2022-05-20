package com.fleam.movieservice.repository;

import com.fleam.movieservice.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    public Set<Genre> findAllByNameIn(Set<String> names);
}
