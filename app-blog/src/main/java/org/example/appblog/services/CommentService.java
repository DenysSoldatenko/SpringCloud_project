package org.example.appblog.services;

import java.util.List;
import org.example.appblog.dtos.CommentDto;

/**
 * Service interface for managing comments in the application.
 */
public interface CommentService {
  CommentDto createComment(Long postId, CommentDto commentDto);

  List<CommentDto> getAllCommentsByPostId(Long postId);

  CommentDto getCommentByPostId(Long postId, Long commentId);

  CommentDto updateCommentByPostId(Long postId, Long commentId, CommentDto commentRequest);

  void deleteCommentByPostId(Long postId, Long commentId);
}
