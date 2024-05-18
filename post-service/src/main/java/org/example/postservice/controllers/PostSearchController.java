package org.example.postservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.postservice.dtos.PostDto;
import org.example.postservice.services.PostSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for searching blog posts.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/search")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Post Search Controller", description = "Handles search for blog posts")
public class PostSearchController {

  private final PostSearchService postSearchService;

  @Operation(summary = "Search posts using full-text search with TSV")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of blog posts",
      content = {
          @Content(mediaType = "application/json",
          schema = @Schema(implementation = PostDto.class))
      }),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @GetMapping()
  public List<PostDto> searchPostsByTsv(
      @RequestParam String query,
      @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
  ) {
    return postSearchService.searchByFullTextUsingTsv(query, pageNo, pageSize);
  }
}
