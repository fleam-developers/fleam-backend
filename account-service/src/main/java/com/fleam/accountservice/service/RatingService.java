package com.fleam.accountservice.service;

import com.fleam.accountservice.dto.RatingForm;
import com.fleam.accountservice.entity.Rating;
import com.fleam.accountservice.entity.User;
import com.fleam.accountservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;

@Service
public class RatingService implements IRatingService{
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Rating createRating(long movieId, RatingForm ratingForm){
        User user = entityManager.getReference(User.class, ratingForm.userId);
        Rating rating = new Rating(null, movieId, user, ratingForm.rating);
        ratingRepository.save(rating);
        return rating;
    }


    @Override
    public List<Rating> getAllRatingsOfMovie(long movieId) {
        return ratingRepository.findAllByMovieId(movieId);
    }

    @Override
    public Rating getRatingOfUser(long movieId, long userId) {
        return ratingRepository.findByUserIdAndMovieId(userId, movieId).get(0);
    }

    @Override
    public HashMap<Long, Integer> getAllRatingsOfUser(long userId) {
        HashMap<Long, Integer> ratingsMap = new HashMap<>();
        ratingRepository.findAllByUserId(userId).forEach(rating -> ratingsMap.put(rating.getMovieId(), rating.getRating()));
        return ratingsMap;
    }

    @Override
    public float getAverageRating(long movieId) {
        List<Rating> ratings = ratingRepository.findAllByMovieId(movieId);
        if (ratings.size()==0){return 0;}
        long sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getRating();
        }
        return  (float) sum / (float) ratings.size();
    }
}
