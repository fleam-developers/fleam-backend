package com.fleam.accountservice.service;

import com.fleam.accountservice.dto.RegisterForm;
import com.fleam.accountservice.dto.WatchingForm;
import com.fleam.accountservice.entity.User;
import com.fleam.accountservice.entity.Watching;

import java.util.Date;
import java.util.List;

public interface IUserService {

    public void createUser(RegisterForm registerForm);

    public User getUserById(long id);

    public List<Watching> getWatchings(long userId);

    public Watching getWatchingForMovie(long userId, long movieId);

    public Watching createWatching(WatchingForm watchingForm);


}
