package com.example.restfulblogapplication.repositories;

import com.example.restfulblogapplication.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Post} entities in the database.
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
