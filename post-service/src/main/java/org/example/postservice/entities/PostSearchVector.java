package org.example.postservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity class representing the full-text search index for posts.
 */
@Data
@Entity
@Table(name = "post_search_vector")
public class PostSearchVector {

  @Id
  private Long postId;

  @Column(name = "search_vector_title", columnDefinition = "tsvector")
  private String searchVectorTitle;

  @Column(name = "search_vector_description", columnDefinition = "tsvector")
  private String searchVectorDescription;

  @Column(name = "search_vector_content", columnDefinition = "tsvector")
  private String searchVectorContent;

  @OneToOne
  @MapsId
  @JoinColumn(name = "post_id")
  private Post post;
}
