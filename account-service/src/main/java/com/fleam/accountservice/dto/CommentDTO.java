package com.fleam.accountservice.dto;

import com.fleam.accountservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    public Long id;
    public Long movieId;
    public String comment;
    public String username;

    public void setUsername(User user){
        this.username = user.getUsername();
    }

    public void setUsername(String username){
        this.username = username;
    }

}
