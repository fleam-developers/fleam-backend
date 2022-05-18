package com.fleam.accountservice.service;

import com.fleam.accountservice.dto.CommentForm;
import com.fleam.accountservice.entity.User;
import com.fleam.accountservice.entity.Comment;
import com.fleam.accountservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class CommentService implements ICommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Comment> getCommentsOfMovie(long movieId) {
        return commentRepository.findByMovieId(movieId);
    }

    public Comment createComment(long movieId, CommentForm commentForm){
        // TODO check movieId from movie-service
        User acc = entityManager.getReference(User.class, commentForm.userId);
        Comment commentObj = new Comment(null, movieId, acc, commentForm.comment);
        commentRepository.save(commentObj);
        return commentObj;
    }


}
