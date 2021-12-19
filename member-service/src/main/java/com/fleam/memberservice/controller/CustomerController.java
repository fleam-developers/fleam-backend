package com.fleam.memberservice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerController
{
    @GetMapping("/")
    public String handleHome(){
        return "member application";
    }

    @GetMapping("/members")
    public String members(){
        return ".";
    }

}
