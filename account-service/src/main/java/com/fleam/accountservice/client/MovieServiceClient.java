package com.fleam.accountservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class MovieServiceClient {
    @Autowired
    public RestTemplate restTemplate;

    public boolean doesMovieExists(long movieId){
        String resp = restTemplate.getForObject("http://movie-service/movie?movieId="+String.valueOf(movieId), String.class);
        System.out.println(resp);
        return true;
    }
}
