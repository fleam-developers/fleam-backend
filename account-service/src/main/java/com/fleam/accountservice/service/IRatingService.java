package com.fleam.accountservice.service;

import com.fleam.accountservice.dto.RatingForm;
import com.fleam.accountservice.entity.Rating;

import java.util.List;

public interface IRatingService {
    public Rating createRating(long movieId, RatingForm ratingForm);
    public List<Rating> getAllRatingsOfMovie(long movieId);
    public Rating getRatingOfUser(long movieId, long userId);
    public List<Rating> getAllRatingsOfUser(long userId);
    public float getAverageRating(long movieId);



}
