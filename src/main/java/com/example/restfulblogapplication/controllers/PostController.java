package com.example.restfulblogapplication.controllers;

import com.example.restfulblogapplication.dtos.PostDto;
import com.example.restfulblogapplication.services.PostService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing blog posts.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Post Controller", description = "Handles blog post management")
public class PostController {

  private final PostService postService;

  @Operation(summary = "Create a new blog post")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Post created successfully",
      content = {
          @Content(mediaType = "application/json",
          schema = @Schema(implementation = PostDto.class))
      }),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
    return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
  }

  @Operation(summary = "Get all blog posts")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of blog posts",
      content = {
          @Content(mediaType = "application/json",
          schema = @Schema(implementation = PostDto.class))
      }),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @GetMapping
  public List<PostDto> getAllPosts(
      @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
      @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
      @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
  ) {
    return postService.getAllPostPaginated(pageNo, pageSize, sortBy, sortDir);
  }

  @Operation(summary = "Get a specific blog post by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Blog post retrieved successfully",
      content = {
          @Content(mediaType = "application/json",
          schema = @Schema(implementation = PostDto.class))
      }),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
      @ApiResponse(responseCode = "404", description = "Post not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(postService.getPostById(id));
  }

  @Operation(summary = "Update a blog post by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Post updated successfully",
      content = {
          @Content(mediaType = "application/json",
          schema = @Schema(implementation = PostDto.class))
      }),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
      @ApiResponse(responseCode = "404", description = "Post not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
                                            @PathVariable(name = "id") Long id) {
    PostDto postResponse = postService.updatePostById(postDto, id);
    return new ResponseEntity<>(postResponse, HttpStatus.OK);
  }

  @Operation(summary = "Delete a blog post by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Post deleted successfully"),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
      @ApiResponse(responseCode = "404", description = "Post not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
    postService.deletePostById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
