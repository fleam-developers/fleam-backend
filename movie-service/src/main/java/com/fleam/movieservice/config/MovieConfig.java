package com.fleam.movieservice.config;

import com.fleam.movieservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Mapper mapper() {return new Mapper(new ModelMapper());}

}
