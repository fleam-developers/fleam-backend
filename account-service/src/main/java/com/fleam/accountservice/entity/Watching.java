package com.fleam.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Watching {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "MOVIE_ID")
    private Long movieId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "LEAVED_IN")
    private Long leavedIn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_OPEN")
    private Date lastOpen;

    @Column(name = "COMPLETED")
    private boolean completed;

}
