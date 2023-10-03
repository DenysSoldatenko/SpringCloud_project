package com.example.restfulblogapplication.controllers;

import com.example.restfulblogapplication.dtos.PostDto;
import com.example.restfulblogapplication.dtos.QuizDto;
import com.example.restfulblogapplication.services.QuizSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for searching quizzes.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quizzes/search")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "QuizSearchController", description = "Handles search for quizzes")
public class QuizSearchController {

  private final QuizSearchService quizSearchService;

  @Operation(summary = "Search quizzes by category using ES")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "List of quizzes",
      content = {
        @Content(mediaType = "application/json",
          schema = @Schema(implementation = PostDto.class))
      }),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @GetMapping("/category")
  public List<QuizDto> searchQuizzesByCategory(
      @RequestParam String query,
      @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
  ) {
    return quizSearchService.findByCategory(query, pageNo, pageSize);
  }


  @Operation(summary = "Search quizzes by name using ES")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "List of quizzes",
      content = {
        @Content(mediaType = "application/json",
          schema = @Schema(implementation = PostDto.class))
      }),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @GetMapping("/name")
  public List<QuizDto> searchQuizzesByName(
      @RequestParam String query,
      @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
  ) {
    return quizSearchService.findByName(query, pageNo, pageSize);
  }
}
