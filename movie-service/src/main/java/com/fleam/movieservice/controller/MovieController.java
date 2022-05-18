package com.fleam.movieservice.controller;

import com.fleam.movieservice.dto.MovieDTO;
import com.fleam.movieservice.dto.MovieForm;
import com.fleam.movieservice.entity.Movie;
import com.fleam.movieservice.mapper.Mapper;
import com.fleam.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {


    private final MovieService movieService;
    private final Mapper mapper;

    @GetMapping
    @ResponseBody
    public MovieDTO getMovieDetails(@RequestParam(value = "movieId") long movieId){
        Movie movie = movieService.getMovie(movieId);
        return mapper.objectToDTO(movie, MovieDTO.class);
    }

    @PostMapping
    @ResponseBody
    public MovieDTO createMovie(@RequestBody MovieForm movieForm){
        return mapper.objectToDTO(movieService.createMovie(movieForm), MovieDTO.class);
    }



    @GetMapping("/stream")
    @ResponseBody
    public ResponseEntity<byte[]> streamMovie(
            @RequestParam(value = "movieId") long movieId,
            @RequestHeader(value = "Range", required = false) String httpRangeList) throws IOException {

        return movieService.streamMovie(movieId, httpRangeList);
    }









}
