package com.fleam.accountservice.controller;

import com.fleam.accountservice.dto.UserDTO;
import com.fleam.accountservice.dto.WatchingDTO;
import com.fleam.accountservice.entity.User;
import com.fleam.accountservice.mapper.Mapper;
import com.fleam.accountservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    // @Value("${my.test.message: default value}")
    // private String testMessage;

    private final UserService userService;
    private final Mapper mapper;

    @GetMapping
    @ResponseBody
    public UserDTO getUser(@RequestParam(name = "userId") long userId){
        User user =  userService.getUserById(userId);
        System.out.println(user.getId());
        return mapper.objectToDTO(user, UserDTO.class);
    }

    @GetMapping(value = "/watching")
    @ResponseBody
    public List<WatchingDTO> getWatchings(@RequestParam(name = "userId") long userId){
        return  mapper.objectsToDTOs(userService.getWatchings(userId), WatchingDTO.class);
    }







}
