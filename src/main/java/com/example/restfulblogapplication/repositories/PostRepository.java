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


  /**
   * Without additional join to comments (N+1 problem):
   * This query retrieves posts and their associated search vectors
   * where the title or description matches the search query.
   * It avoids the N+1 problem by not including comments, but it might have faster response time
   * due to simplicity.
   *
   * <p>With additional join to comments:
   * If uncommented, this JOIN brings in comments associated with each post,
   * eliminating the N+1 problem.
   * However, be aware that it may increase response time due to the added complexity
   * and potentially larger result set.
   *
   * <p>Additionally, it is recommended to create a new index
   * on the columns used in joins and WHERE conditions
   * to optimize the query performance.
   *
   * <p>To include comments and remove the N+1 problem: JOIN comments c ON p.id = c.post_id
   *
   * @param query The search query.
   * @param pageable The pagination information for the result set.
   * @return A Page of Post entities matching the search criteria.
   */
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
}
