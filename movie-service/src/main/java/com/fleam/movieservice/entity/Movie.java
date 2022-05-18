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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "GENRE")
    private String genre;

    @Column(name = "DESCRIPTION")
    private String description;


    public ClassPathResource getContentPath(){
        return new ClassPathResource(ServiceConstants.MOVIES_PATH + "/" + this.id + ServiceConstants.CONTENT_FORMAT);
    }

    public long getContentSize() throws IOException {
        File file = this.getContentPath().getFile();
        return file.length();
    }

}
