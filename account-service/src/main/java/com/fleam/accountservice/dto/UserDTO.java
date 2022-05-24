package com.fleam.accountservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    public Long id;
    public String username;
    public String email;
    public boolean creator;
    public boolean admin;
}
