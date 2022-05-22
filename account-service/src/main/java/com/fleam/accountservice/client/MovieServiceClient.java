package com.fleam.accountservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class MovieServiceClient {
    @Autowired
    public RestTemplate restTemplate;

    public boolean doesMovieExists(long movieId){
        boolean resp = Boolean.TRUE.equals(restTemplate.getForObject("http://movie-service/movie/check?movieId=" + String.valueOf(movieId), boolean.class));
        System.out.println(resp);
        return resp;
    }
}
