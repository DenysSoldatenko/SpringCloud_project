package org.example.appblog.entities;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity class representing a comment associated with a blog post.
 */
@Data
@Entity
@Table(name = "comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String name;

  private String email;

  private String body;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;
}
