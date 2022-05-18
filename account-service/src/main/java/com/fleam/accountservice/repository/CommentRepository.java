package com.fleam.accountservice.repository;

import com.fleam.accountservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByMovieId(long movieId);

}
