package com.fleam.movieservice;

import com.fleam.movieservice.entity.Movie;
import com.fleam.movieservice.repository.MovieRepository;
import com.fleam.movieservice.util.DatabasePopulate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableEurekaClient
public class MovieServiceApplication{

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	DatabasePopulate databasePopulate;

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}


	@PostConstruct
	public void populateDatabase(){
		if (databasePopulate.isPopulateGenres){
			System.out.println("Populating genres");
			databasePopulate.populateGenres();
		}
		if (databasePopulate.isPopulateMovies){
			System.out.println("Populating movies");
			try{
				databasePopulate.populateMovies();
			}catch (Exception e){
				e.printStackTrace();
			}
		}

	}

}
