package com.fleam.movieservice.service;

import com.fleam.movieservice.dto.CatalogDTO;
import com.fleam.movieservice.dto.GenreDTO;

import java.util.List;

public interface ICatalogService {

    public CatalogDTO getAllGenreCatalog();

    public CatalogDTO getGenreCatalog(long genreId);

    public CatalogDTO getHistoryCatalog(String authHeader, Long userId);

    public CatalogDTO getRecommendationCatalog(String authHeader, Long userId);

}
