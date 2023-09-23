package com.example.restfulblogapplication.services.impl;

import com.example.restfulblogapplication.dtos.CommentDto;
import com.example.restfulblogapplication.entities.Comment;
import com.example.restfulblogapplication.entities.Post;
import com.example.restfulblogapplication.exceptions.BlogApiException;
import com.example.restfulblogapplication.exceptions.CommentNotFoundException;
import com.example.restfulblogapplication.exceptions.PostNotFoundException;
import com.example.restfulblogapplication.mappers.CommentMapper;
import com.example.restfulblogapplication.repositories.CommentRepository;
import com.example.restfulblogapplication.repositories.PostRepository;
import com.example.restfulblogapplication.services.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private static final String POST_NOT_FOUND_MESSAGE = "Post not found with id: ";
  private static final String COMMENT_NOT_FOUND_MESSAGE = "Comment not found with id: ";
  private static final String COMMENT_DOES_NOT_BELONG_MESSAGE = "Comment does not belong to post";

  private final CommentRepository commentRepository;
  private final PostRepository postRepository;
  private final CommentMapper commentMapper;

  @Override
  public CommentDto createComment(Long postId, CommentDto commentDto) {
    Comment comment = commentMapper.toModel(commentDto);
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_MESSAGE + postId));
    comment.setPost(post);
    Comment newComment = commentRepository.save(comment);

    return commentMapper.toDto(newComment);
  }

  @Override
  public List<CommentDto> getAllCommentsByPostId(Long postId) {
    List<Comment> comments = commentRepository.findByPostId(postId);
    return comments.stream().map(commentMapper::toDto).toList();
  }

  @Override
  public CommentDto getCommentByPostId(Long postId, Long commentId) {
    Comment comment = validateCommentBelongsToPost(postId, commentId);

    return commentMapper.toDto(comment);
  }

  @Override
  public CommentDto updateCommentByPostId(Long postId, Long commentId, CommentDto commentRequest) {
    Comment comment = validateCommentBelongsToPost(postId, commentId);

    comment.setName(commentRequest.name());
    comment.setEmail(commentRequest.email());
    comment.setBody(commentRequest.body());

    Comment updatedComment = commentRepository.save(comment);
    return commentMapper.toDto(updatedComment);
  }

  @Override
  public void deleteCommentByPostId(Long postId, Long commentId) {
    Comment comment = validateCommentBelongsToPost(postId, commentId);

    commentRepository.delete(comment);
  }

  private Comment validateCommentBelongsToPost(Long postId, Long commentId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_MESSAGE + postId));

    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND_MESSAGE + commentId));

    if (!comment.getPost().getId().equals(post.getId())) {
      throw new BlogApiException(COMMENT_DOES_NOT_BELONG_MESSAGE);
    }
    return comment;
  }
}
