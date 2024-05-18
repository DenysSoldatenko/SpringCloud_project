package org.example.postservice.controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.postservice.dtos.CommentDto;
import org.example.postservice.services.CommentService;
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
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Comment Controller", description = "Handles comments related to blog posts")
public class CommentController {

  private final CommentService commentService;

  @Operation(summary = "Create a comment for a blog post")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Comment created successfully",
      content = {
          @Content(mediaType = "application/json",
          schema = @Schema(implementation = CommentDto.class))
      }),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
      @ApiResponse(responseCode = "404", description = "Post not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PostMapping("/{postId}/comments")
  public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                  @Valid @RequestBody CommentDto commentDto) {
    CommentDto comment = commentService.createComment(postId, commentDto);
    return new ResponseEntity<>(comment, CREATED);
  }

  @Operation(summary = "Get all comments for a blog post")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of comments",
      content = {
          @Content(mediaType = "application/json",
          schema = @Schema(implementation = CommentDto.class))
      }),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
      @ApiResponse(responseCode = "404", description = "Post not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @GetMapping("/{postId}/comments")
  public List<CommentDto> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId) {
    return commentService.getAllCommentsByPostId(postId);
  }

  @Operation(summary = "Get a specific comment for a blog post")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Comment retrieved successfully",
      content = {
          @Content(mediaType = "application/json",
          schema = @Schema(implementation = CommentDto.class))
      }),
      @ApiResponse(
        responseCode = "401", description = "Unauthorized", content = @Content
      ),
      @ApiResponse(
        responseCode = "404", description = "Post or comment not found", content = @Content
      ),
      @ApiResponse(
        responseCode = "500", description = "Internal server error", content = @Content
      )
  })
  @GetMapping("/{postId}/comments/{id}")
  public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
                                                   @PathVariable(value = "id") Long commentId) {
    CommentDto commentDto = commentService.getCommentByPostId(postId, commentId);
    return new ResponseEntity<>(commentDto, OK);
  }

  @Operation(summary = "Update a comment for a blog post")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Comment updated successfully",
      content = {
          @Content(mediaType = "application/json",
          schema = @Schema(implementation = CommentDto.class))
      }),
      @ApiResponse(
        responseCode = "400", description = "Bad request", content = @Content
      ),
      @ApiResponse(
        responseCode = "401", description = "Unauthorized", content = @Content
      ),
      @ApiResponse(
        responseCode = "403", description = "Forbidden", content = @Content
      ),
      @ApiResponse(
        responseCode = "404", description = "Post or comment not found", content = @Content
      ),
      @ApiResponse(
        responseCode = "500", description = "Internal server error", content = @Content
      )
  })
  @PutMapping("/{postId}/comments/{id}")
  public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                  @PathVariable(value = "id") Long commentId,
                                                  @Valid @RequestBody CommentDto commentDto) {
    CommentDto updatedComment = commentService.updateCommentByPostId(postId, commentId, commentDto);
    return new ResponseEntity<>(updatedComment, OK);
  }

  @Operation(summary = "Delete a comment for a blog post")
  @ApiResponses(value = {
      @ApiResponse(
        responseCode = "204", description = "Comment deleted successfully"
      ),
      @ApiResponse(
        responseCode = "401", description = "Unauthorized", content = @Content
      ),
      @ApiResponse(
        responseCode = "403", description = "Forbidden", content = @Content
      ),
      @ApiResponse(
        responseCode = "404", description = "Post or comment not found", content = @Content
      ),
      @ApiResponse(
        responseCode = "500", description = "Internal server error", content = @Content
      )
  })
  @DeleteMapping("/{postId}/comments/{id}")
  public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                              @PathVariable(value = "id") Long commentId) {
    commentService.deleteCommentByPostId(postId, commentId);
    return new ResponseEntity<>(NO_CONTENT);
  }
}
