package com.fleam.accountservice.controller;

import com.fleam.accountservice.client.MovieServiceClient;
import com.fleam.accountservice.dto.CommentDTO;
import com.fleam.accountservice.dto.CommentForm;
import com.fleam.accountservice.dto.RatingDTO;
import com.fleam.accountservice.dto.RatingForm;
import com.fleam.accountservice.mapper.Mapper;
import com.fleam.accountservice.service.CommentService;
import com.fleam.accountservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final Mapper mapper;
    private final CommentService commentService;
    private final RatingService ratingService;
    private final MovieServiceClient movieServiceClient;

    @GetMapping("/comment/{movieId}")
    @ResponseBody
    public List<CommentDTO> getCommentsForMovie(@PathVariable long movieId){
        return commentService.getCommentsOfMovie(movieId);
    }

    @PostMapping("/comment/{movieId}")
    @ResponseBody
    public CommentDTO createComment(@PathVariable long movieId, @RequestBody CommentForm commentForm){
        return mapper.objectToDTO(commentService.createComment(movieId, commentForm), CommentDTO.class);
    }

    @GetMapping("/rating/{movieId}")
    @ResponseBody
    public List<RatingDTO> getAllRatingsOfMovie(@PathVariable long movieId){
        return mapper.objectsToDTOs(ratingService.getAllRatingsOfMovie(movieId), RatingDTO.class);
    }

    @GetMapping("/rating/{movieId}/average")
    @ResponseBody
    public Float getAverageRatingOfMovie(@PathVariable long movieId){
        return ratingService.getAverageRating(movieId);
    }

    @GetMapping("/rating/{movieId}/{userId}")
    @ResponseBody
    public RatingDTO getRatingOfMovieForUser(@PathVariable long movieId, @PathVariable long userId){
        return mapper.objectToDTO(ratingService.getRatingOfUser(movieId, userId), RatingDTO.class);
    }

    @PostMapping("/rating/{movieId}")
    @ResponseBody
    public RatingDTO rateMovie(@PathVariable long movieId, @RequestBody RatingForm ratingForm){
        return mapper.objectToDTO(ratingService.createRating(movieId, ratingForm), RatingDTO.class);

    }

}
