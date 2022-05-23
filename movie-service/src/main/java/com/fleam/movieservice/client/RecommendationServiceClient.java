package com.fleam.movieservice.client;

import com.fleam.movieservice.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RecommendationServiceClient {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public Mapper mapper;

    public static final int NUM_OF_RECOMS = 10;

    public List<Long> getRecommendationsForUser(long userId, String authHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Long[]> response = null;
        try {
            response = restTemplate.exchange(
                    "http://recommendation-service/recommendation/user/?userId=" + userId + "&numOfRecommendations=" + NUM_OF_RECOMS,
                    HttpMethod.GET, requestEntity, Long[].class);
        } catch (HttpClientErrorException.Forbidden e) {
            e.printStackTrace();
            return null;
        }
        return Arrays.asList(response.getBody());
    }

    public List<Long> getRecommendationsForMovie(long movieId, String authHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Long[]> response = null;
        try {
            response = restTemplate.exchange(
                    "http://recommendation-service//recommendation/movie/?movieId=" + movieId + "&numOfRecommendations=" + NUM_OF_RECOMS,
                    HttpMethod.GET, requestEntity, Long[].class);
        } catch (HttpClientErrorException.Forbidden e) {
            e.printStackTrace();
            return null;
        }
        return Arrays.asList(response.getBody());
    }


}
