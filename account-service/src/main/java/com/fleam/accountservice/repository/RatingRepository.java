package com.fleam.accountservice.repository;

import com.fleam.accountservice.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findAllByMovieId(long movieId);
    List<Rating> findAllByUserId(long userId);
    List<Rating> findByUserIdAndMovieId(long userId, long movieId);





}
