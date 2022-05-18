package com.fleam.accountservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    public Long id;
    public Long movieId;
    public String comment;
    public UserDTO user;
}
