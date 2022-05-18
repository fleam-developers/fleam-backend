package com.fleam.movieservice;

import com.fleam.movieservice.entity.Movie;
import com.fleam.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieServiceApplication{

	@Autowired
	MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}
}
