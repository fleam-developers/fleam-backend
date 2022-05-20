package com.fleam.movieservice.entity;

import com.fleam.movieservice.constants.ServiceConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "POSTER_URL")
    private String poster_url;


    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    Set<Genre> genres;


    public ClassPathResource getContentPath(){
        return new ClassPathResource(ServiceConstants.MOVIES_PATH + "/" + this.id + ServiceConstants.CONTENT_FORMAT);
    }

    public long getContentSize() throws IOException {
        File file = this.getContentPath().getFile();
        return file.length();
    }

}
