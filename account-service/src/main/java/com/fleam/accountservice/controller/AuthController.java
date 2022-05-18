package com.fleam.accountservice.controller;

import com.fleam.accountservice.auth.TokenManager;
import com.fleam.accountservice.dto.LoginForm;
import com.fleam.accountservice.dto.RegisterForm;
import com.fleam.accountservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping(path="/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.username, loginForm.password));
            return new ResponseEntity<String>(TokenManager.generateToken(loginForm.username), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody RegisterForm registerForm){
        userService.createUser(registerForm);
        return new ResponseEntity<String>("Successfully registered", HttpStatus.OK);
    }

}
