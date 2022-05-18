package com.fleam.accountservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RatingDTO {
    public Long id;
    public Long movieId;
    public int rating;
    public UserDTO user;
}
