package com.fleam.movieservice.controller;

import com.fleam.movieservice.dto.MovieDTO;
import com.fleam.movieservice.dto.MovieDetailsDTO;
import com.fleam.movieservice.dto.CreateMovieForm;
import com.fleam.movieservice.mapper.Mapper;
import com.fleam.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {


    private final MovieService movieService;
    private final Mapper mapper;

    @GetMapping
    @ResponseBody
    public MovieDetailsDTO getMovieDetails(@RequestParam(value = "movieId") long movieId,
                                           @RequestHeader("Authorization") String authHeader){
        return movieService.getMovieDetails(movieId, authHeader);
    }

    @GetMapping("/check")
    @ResponseBody
    public boolean doesMovieExists(@RequestParam(value = "movieId") long movieId){
        try{
            MovieDTO movie = movieService.getMovie(movieId);
            return movie != null;
        }
        catch (Exception e){
            return false;
        }
    }


    @GetMapping("/search")
    @ResponseBody
    public List<MovieDTO> searchMovieByName(@RequestParam(value="name") String name){
        return mapper.objectsToDTOs(movieService.searchMovieByName(name), MovieDTO.class);
    }


    @PostMapping
    @ResponseBody
    public MovieDTO createMovie(@RequestBody CreateMovieForm movieForm){
        return mapper.objectToDTO(movieService.createMovie(movieForm), MovieDTO.class);
    }


    @PostMapping("/upload")
    public ResponseEntity<Object> uploadMovie(
            @RequestParam(value = "movieId") long movieId,
            @RequestBody MultipartFile movieFile){ // TODO: multiform shit here
        boolean result = movieService.uploadMovie(movieId, movieFile);
        if (result){
            return ResponseEntity.ok("Video uploaded");
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }


    @GetMapping("/stream")
    @ResponseBody
    public ResponseEntity<byte[]> streamMovie(
            @RequestParam(value = "movieId") long movieId,
            @RequestHeader(value = "Range", required = false) String httpRangeList) throws IOException {
        return movieService.streamMovie(movieId, httpRangeList);
    }









}
