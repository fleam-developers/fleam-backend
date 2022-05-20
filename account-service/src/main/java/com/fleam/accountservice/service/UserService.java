package com.fleam.accountservice.service;


import com.fleam.accountservice.dto.RegisterForm;
import com.fleam.accountservice.dto.WatchingForm;
import com.fleam.accountservice.entity.User;
import com.fleam.accountservice.entity.Watching;
import com.fleam.accountservice.repository.UserRepository;
import com.fleam.accountservice.repository.WatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WatchingRepository watchingRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createUser(RegisterForm registerForm) {
        User user = new User();
        user.setUsername(registerForm.username);
        user.setEmail(registerForm.email);
        user.setPassword(bCryptPasswordEncoder.encode(registerForm.password));
        userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<Watching> getWatchings(long userId) {
        return watchingRepository.findAllByUserId(userId);
    }

    @Override
    public Watching getWatchingForMovie(long userId, long movieId) {
        return null;
    }

    @Override
    public Watching createWatching(WatchingForm watchingForm){
        User acc = entityManager.getReference(User.class, watchingForm.userId);
        Watching watching = new Watching(null, watchingForm.movieId,
                acc, 0L, new Date(System.currentTimeMillis()), false);
        watchingRepository.save(watching);
        return watching;
    }

}
