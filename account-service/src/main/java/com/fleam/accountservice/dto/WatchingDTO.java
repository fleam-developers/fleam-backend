package com.fleam.accountservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class WatchingDTO {
    public Long id;
    public Long movieId;
    public Long leavedIn;
    public Date lastOpen;
    public boolean completed;
    public UserDTO user;
}
