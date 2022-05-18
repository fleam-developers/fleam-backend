package com.fleam.accountservice.config;

import com.fleam.accountservice.client.MovieServiceClient;
import com.fleam.accountservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public Mapper mapper() {return new Mapper(new ModelMapper());}

    @Bean
    public MovieServiceClient movieServiceClient() {return new MovieServiceClient();}


    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){return new RestTemplate();}

}
