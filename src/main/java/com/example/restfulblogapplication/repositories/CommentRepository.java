package com.example.restfulblogapplication.repositories;

import com.example.restfulblogapplication.entities.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Comment} entities in the database.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByPostId(Long postId);
}