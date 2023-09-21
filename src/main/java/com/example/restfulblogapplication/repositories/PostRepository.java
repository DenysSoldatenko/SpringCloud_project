package com.example.restfulblogapplication.repositories;

import com.example.restfulblogapplication.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
