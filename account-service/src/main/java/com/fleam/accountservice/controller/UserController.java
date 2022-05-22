package com.fleam.accountservice.controller;

import com.fleam.accountservice.dto.BecomeCreatorForm;
import com.fleam.accountservice.dto.UserDTO;
import com.fleam.accountservice.dto.WatchingDTO;
import com.fleam.accountservice.dto.WatchingForm;
import com.fleam.accountservice.entity.User;
import com.fleam.accountservice.entity.Watching;
import com.fleam.accountservice.mapper.Mapper;
import com.fleam.accountservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final Mapper mapper;

    @GetMapping
    @ResponseBody
    public UserDTO getUser(@RequestParam(name = "userId") long userId){
        User user =  userService.getUserById(userId);
        System.out.println(user.getId());
        return mapper.objectToDTO(user, UserDTO.class);
    }

    @GetMapping(value = "/check")
    @ResponseBody
    public boolean isUserAuthorized(){
        return true;
    }

    @GetMapping(value = "/watching")
    @ResponseBody
    public List<WatchingDTO> getWatchings(@RequestParam(name = "userId") long userId){
        return  mapper.objectsToDTOs(userService.getWatchings(userId), WatchingDTO.class);
    }

    @PostMapping(value = "/watching")
    @ResponseBody
    public WatchingDTO createWatching(@RequestBody WatchingForm watchingForm){
        return mapper.objectToDTO(userService.createWatching(watchingForm), WatchingDTO.class);
    }

    @GetMapping(value = "/history")
    @ResponseBody
    public List<Long> getHistory(@RequestParam(name = "userId") long userId){
        return userService.getWatchings(userId).stream().map(Watching::getMovieId).collect(Collectors.toList());
    }

    @PostMapping(value = "/creator")
    @ResponseBody
    public UserDTO becomeCreator(@RequestBody BecomeCreatorForm user){
        return mapper.objectToDTO(userService.becomeCreator(user), UserDTO.class);
    }







}
