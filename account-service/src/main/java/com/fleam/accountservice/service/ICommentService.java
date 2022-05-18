package com.fleam.accountservice.service;

import com.fleam.accountservice.dto.CommentDTO;
import com.fleam.accountservice.dto.CommentForm;
import com.fleam.accountservice.entity.Comment;

import java.util.List;

public interface ICommentService {
    public List<Comment> getCommentsOfMovie(long movieId);
    public Comment createComment(long movieId, CommentForm commentForm);
}
