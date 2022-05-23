package com.fleam.movieservice.service;

import com.fleam.movieservice.dto.CatalogDTO;
import com.fleam.movieservice.dto.GenreDTO;

import java.util.List;

public interface ICatalogService {

    public CatalogDTO getGenreCatalog();

    public CatalogDTO getHistoryCatalog(String authHeader, Long userId);

    public CatalogDTO getRecommendationCatalog(String authHeader, Long userId);

}
