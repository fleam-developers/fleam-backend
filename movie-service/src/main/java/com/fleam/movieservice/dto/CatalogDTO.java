package com.fleam.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
public class CatalogDTO {
    public List<GenreDTO> genres;

    public void joinCatalogs(CatalogDTO otherCatalog){
        this.genres = Stream.concat(otherCatalog.genres.stream(), this.genres.stream())
                .collect(Collectors.toList());
    }
}
