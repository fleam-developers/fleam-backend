package com.fleam.accountservice.service;

import com.fleam.accountservice.entity.User;
import com.fleam.accountservice.repository.CommentRepository;
import com.fleam.accountservice.repository.RatingRepository;
import com.fleam.accountservice.repository.UserRepository;
import com.fleam.accountservice.repository.WatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private WatchingRepository watchingRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    public Long getTotalWatchCount(){
        return watchingRepository.count();
    }

    public Long getTotalRatingCount(){
        return ratingRepository.count();
    }

    public Long getTotalCommentCount(){
        return commentRepository.count();
    }

    public List<User> getAllCreators(){
        return userRepository.findByCreatorTrue();
    }

    public boolean isAdmin(){
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        System.out.println(username);
        User user = userRepository.findByUsername(username);
        if (user == null){
            return false;
        }
        return user.isAdmin();
    }

    public User revertCreator(long userId){
        try {
            User user = userRepository.getById(userId);
            user.setCreator(false);
            userRepository.save(user);
            return user;
        }catch (Exception e){
            return null;
        }
    }


}
