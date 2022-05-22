package com.fleam.movieservice.client;

import com.fleam.movieservice.dto.MovieCommentDTO;
import com.fleam.movieservice.dto.MovieDetailsDTO;
import com.fleam.movieservice.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class AccountServiceClient {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public Mapper mapper;

    public boolean isUserAuthorized(String authHeader){
        // TODO redis cache here
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        boolean resp;
        try{
            ResponseEntity<Boolean> response = restTemplate.exchange(
                    "http://account-service/user/check", HttpMethod.GET, requestEntity, Boolean.class);
        }
        catch (HttpClientErrorException.Forbidden e){
            return false;
        }
        return true;
    }

    public Long[] getHistoryOfUser(String authHeader, Long userId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        try{
            ResponseEntity<Long[]> response = restTemplate.exchange(
                    "http://account-service/user/history?userId="+userId, HttpMethod.GET, requestEntity, Long[].class);
            return response.getBody();
        }
        catch (HttpClientErrorException.Forbidden e){
            return null;
        }
    }

    public List<MovieCommentDTO> getCommentsOfMovie(String authHeader, Long movieId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        try{
            ResponseEntity<Object[]> response = restTemplate.exchange(
                    "http://account-service/movie/comment/"+movieId, HttpMethod.GET, requestEntity, Object[].class);
            List<MovieCommentDTO> comments = mapper.objectsToDTOs(Arrays.asList(response.getBody()), MovieCommentDTO.class);
            return comments;
        }
        catch (HttpClientErrorException.Forbidden e){
            return null;
        }

    }

    public Float getAverageRatingForMovie(String authHeader, Long movieId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        try{
            ResponseEntity<Float> response = restTemplate.exchange(
                    "http://account-service/movie/rating/"+movieId+"/average", HttpMethod.GET, requestEntity, Float.class);
            return response.getBody();
        }
        catch (HttpClientErrorException.Forbidden e){
            return null;
        }
    }
    
}
