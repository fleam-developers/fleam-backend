package com.fleam.movieservice.repository;

import com.fleam.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query(value = "SELECT max(id) FROM Movie")
    Long maxId();

}
