package com.fleam.accountservice.service;

import com.fleam.accountservice.dto.CommentDTO;
import com.fleam.accountservice.dto.CommentForm;
import com.fleam.accountservice.entity.User;
import com.fleam.accountservice.entity.Comment;
import com.fleam.accountservice.repository.CommentRepository;
import com.fleam.accountservice.repository.WatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private WatchingRepository watchingRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<CommentDTO> getCommentsOfMovie(long movieId) {
        List<Comment> comments = commentRepository.findByMovieId(movieId);
        List<CommentDTO> result = new ArrayList<>();
        for (Comment com : comments) {
            CommentDTO commentDTO = new CommentDTO(com.getId(), com.getMovieId(), com.getComment(), com.getUser().getUsername());
            result.add(commentDTO);
        }
        return result;
    }

    public Comment createComment(long movieId, CommentForm commentForm){
        // TODO check movieId from movie-service
        User acc = entityManager.getReference(User.class, commentForm.userId);
        Comment commentObj = new Comment(null, movieId, acc, commentForm.comment);
        commentRepository.save(commentObj);
        return commentObj;
    }




}
