package com.example.restfulblogapplication.controllers;

import com.example.restfulblogapplication.dtos.CommentDto;
import com.example.restfulblogapplication.services.CommentService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing comments related to blog posts.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/{postId}/comments")
  public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                  @Valid @RequestBody CommentDto commentDto) {
    CommentDto comment = commentService.createComment(postId, commentDto);
    return new ResponseEntity<>(comment, HttpStatus.CREATED);
  }

  @GetMapping("/{postId}/comments")
  public List<CommentDto> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId) {
    return commentService.getAllCommentsByPostId(postId);
  }

  @GetMapping("/{postId}/comments/{id}")
  public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
                                                   @PathVariable(value = "id") Long commentId) {
    CommentDto commentDto = commentService.getCommentByPostId(postId, commentId);
    return new ResponseEntity<>(commentDto, HttpStatus.OK);
  }

  @PutMapping("/{postId}/comments/{id}")
  public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                  @PathVariable(value = "id") Long commentId,
                                                  @Valid @RequestBody CommentDto commentDto) {
    CommentDto updatedComment = commentService.updateCommentByPostId(postId, commentId, commentDto);
    return new ResponseEntity<>(updatedComment, HttpStatus.OK);
  }

  @DeleteMapping("/{postId}/comments/{id}")
  public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                              @PathVariable(value = "id") Long commentId) {
    commentService.deleteCommentByPostId(postId, commentId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
