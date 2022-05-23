package com.fleam.movieservice.controller;

import com.fleam.movieservice.client.AccountServiceClient;
import com.fleam.movieservice.client.RecommendationServiceClient;
import com.fleam.movieservice.dto.CatalogDTO;
import com.fleam.movieservice.dto.GenreDTO;
import com.fleam.movieservice.entity.Genre;
import com.fleam.movieservice.entity.Movie;
import com.fleam.movieservice.mapper.Mapper;
import com.fleam.movieservice.repository.GenreRepository;
import com.fleam.movieservice.service.CatalogService;
import com.fleam.movieservice.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private RecommendationServiceClient recommendationServiceClient;

    @GetMapping
    @ResponseBody
    public CatalogDTO getCatalogForUser(@RequestParam(value = "userId") long userId,
                                        @RequestHeader("Authorization") String authHeader){
        if (!accountServiceClient.isUserAuthorized(authHeader)) {return null;}
        // recommendation catalog
        CatalogDTO recommendationsCatalog = catalogService.getRecommendationCatalog(authHeader, userId);
        CatalogDTO historyCatalog = catalogService.getHistoryCatalog(authHeader, userId);
        CatalogDTO catalog = catalogService.getGenreCatalog();
        catalog.joinCatalogs(recommendationsCatalog);
        catalog.joinCatalogs(historyCatalog);
        return catalog;
    }
}
