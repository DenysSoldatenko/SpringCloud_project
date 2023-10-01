package com.example.restfulblogapplication.repositories;

import com.example.restfulblogapplication.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for managing {@link Post} entities in the database.
 */
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

  @Query("""
    SELECT p
    FROM Post p
             JOIN FETCH p.postSearchVector
    WHERE
        ts_rank(p.postSearchVector.searchVectorTitle, plainto_tsquery('english', : query)) > 0.0
    ORDER BY ts_rank(p.postSearchVector.searchVectorTitle, plainto_tsquery('english', : query)) DESC
      """)
  Page<Post> searchByFullTextUsingTsv2(@Param("query") String query, Pageable pageable);


  @Query(value = """
      SELECT p.id, p.title, p.description, p.content,
             similarity(psv.search_vector_title::text, :query) AS title_similarity,
             similarity(psv.search_vector_description::text, :query) AS description_similarity
      FROM posts p
               JOIN post_search_vector psv ON p.id = psv.post_id
      WHERE similarity(psv.search_vector_title::text, :query) > 0.1
         OR similarity(psv.search_vector_description::text, :query) > 0.1
      ORDER BY title_similarity DESC, description_similarity DESC;
      """, nativeQuery = true)
  Page<Post> searchByFullTextUsingTsvAndSimilarity(@Param("query") String query, Pageable pageable);
}
