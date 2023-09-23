package com.example.restfulblogapplication.services;

import com.example.restfulblogapplication.dtos.CommentDto;
import java.util.List;

public interface CommentService {
  CommentDto createComment(Long postId, CommentDto commentDto);

  List<CommentDto> getAllCommentsByPostId(Long postId);

  CommentDto getCommentByPostId(Long postId, Long commentId);

  CommentDto updateCommentByPostId(Long postId, Long commentId, CommentDto commentRequest);

  void deleteCommentByPostId(Long postId, Long commentId);
}
