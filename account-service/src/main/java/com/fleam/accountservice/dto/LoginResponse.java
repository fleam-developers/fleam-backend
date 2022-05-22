package com.fleam.accountservice.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginResponse {
    public String token;
    public UserDTO user;
}
