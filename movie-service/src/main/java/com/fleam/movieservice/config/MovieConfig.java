package com.fleam.movieservice.config;

import com.fleam.movieservice.client.AccountServiceClient;
import com.fleam.movieservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MovieConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Mapper mapper() {return new Mapper(new ModelMapper());}

    @Bean
    public AccountServiceClient accountServiceClient() {return new AccountServiceClient();}

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){return new RestTemplate();}


}
