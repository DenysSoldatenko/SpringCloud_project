package org.example.postservice.services.impl;

import static org.example.postservice.utils.ApplicationConstant.COMMENT_DOES_NOT_BELONG_MESSAGE;
import static org.example.postservice.utils.ApplicationConstant.COMMENT_NOT_FOUND_MESSAGE;
import static org.example.postservice.utils.ApplicationConstant.POST_NOT_FOUND_MESSAGE;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.postservice.dtos.CommentDto;
import org.example.postservice.entities.Comment;
import org.example.postservice.entities.Post;
import org.example.postservice.exceptions.BlogApiException;
import org.example.postservice.exceptions.CommentNotFoundException;
import org.example.postservice.exceptions.PostNotFoundException;
import org.example.postservice.mappers.CommentMapper;
import org.example.postservice.repositories.CommentRepository;
import org.example.postservice.repositories.PostRepository;
import org.example.postservice.services.CommentService;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing comments in the application.
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

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
