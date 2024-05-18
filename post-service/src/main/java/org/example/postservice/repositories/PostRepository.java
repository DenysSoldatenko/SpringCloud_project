package org.example.postservice.repositories;

import java.util.Optional;
import org.example.postservice.dtos.PostDto;
import org.example.postservice.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Post} entities in the database.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  @Query(value = """
      SELECT p.id, p.title, p.description, p.content,
             similarity(p.title, :query)       AS title_similarity,
             similarity(p.description, :query) AS description_similarity
      FROM posts p
      WHERE similarity(p.title, :query) > 0.1
         OR similarity(p.description, :query) > 0.1
      ORDER BY similarity(p.title, :query) DESC, similarity(p.description, :query) DESC;
      """, nativeQuery = true)
  Page<Post> searchByFullTextUsingSimilarity(@Param("query") String query, Pageable pageable);

  @Query(value = """
      SELECT p.id, p.title, p.description, p.content,
             ts_rank(psv.search_vector_title, plainto_tsquery('english', :query)) AS title_rank,
             ts_rank(psv.search_vector_description, plainto_tsquery('english', :query)) AS desc_rank
      FROM posts p
               JOIN post_search_vector psv ON p.id = psv.post_id
      WHERE psv.search_vector_title @@ plainto_tsquery('english', :query)
         OR psv.search_vector_description @@ plainto_tsquery('english', :query)
      ORDER BY title_rank DESC, desc_rank DESC;
      """, nativeQuery = true)
  Page<Post> searchByFullTextUsingTsv(@Param("query") String query, Pageable pageable);

  @Query("SELECT new org.example.postservice.dtos.PostDto(p.id, p.title, p.description, p.content) "
      + "FROM Post p ")
  Page<PostDto> findAllPosts(Pageable pageable);

  @Query("SELECT new org.example.postservice.dtos.PostDto(p.id, p.title, p.description, p.content) "
      + "FROM Post p WHERE p.id = :postId")
  Optional<PostDto> findPostDtoById(@Param("postId") Long postId);

}
