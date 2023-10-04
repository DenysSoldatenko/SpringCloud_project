package org.example.appquiz.controllers;

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
import org.example.appquiz.dtos.AnswerDto;
import org.example.appquiz.dtos.QuizDto;
import org.example.appquiz.dtos.ResultDto;
import org.example.appquiz.services.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * Controller for managing quizzes.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quizzes")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Quiz Controller", description = "Handles quiz management")
public class QuizController {

  private final QuizService quizService;

  @Operation(summary = "Create a new quiz")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Quiz created successfully",
      content = {
        @Content(mediaType = "application/json",
          schema = @Schema(implementation = QuizDto.class))
      }),
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PostMapping
  public ResponseEntity<QuizDto> createQuiz(@Valid @RequestBody QuizDto quizDto) {
    return new ResponseEntity<>(quizService.createQuiz(quizDto), HttpStatus.CREATED);
  }

  @Operation(summary = "Get all quizzes")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "List of quizzes",
      content = {
        @Content(mediaType = "application/json",
          schema = @Schema(implementation = QuizDto.class))
      }),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @GetMapping
  public List<QuizDto> getAllQuizzes(
      @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
      @RequestParam(value = "sortBy", defaultValue = "name.keyword", required = false) String srtBy,
      @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
  ) {
    return quizService.getAllQuizPaginated(pageNo, pageSize, srtBy, sortDir);
  }

  @Operation(summary = "Get a specific quiz by ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Quiz retrieved successfully",
      content = {
        @Content(mediaType = "application/json",
          schema = @Schema(implementation = QuizDto.class))
      }),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    @ApiResponse(responseCode = "404", description = "Quiz not found", content = @Content),
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @GetMapping("/{id}")
  public ResponseEntity<QuizDto> getQuizById(@PathVariable(name = "id") String id) {
    return ResponseEntity.ok(quizService.getQuizById(id));
  }

  @Operation(summary = "Update a quiz by ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Quiz updated successfully",
      content = {
        @Content(mediaType = "application/json",
          schema = @Schema(implementation = QuizDto.class))
      }),
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    @ApiResponse(responseCode = "404", description = "Quiz not found", content = @Content),
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PutMapping("/{id}")
  public ResponseEntity<QuizDto> updateQuiz(@Valid @RequestBody QuizDto quizDto,
                                            @PathVariable(name = "id") String id) {
    QuizDto quizResponse = quizService.updateQuizById(quizDto, id);
    return new ResponseEntity<>(quizResponse, HttpStatus.OK);
  }

  @Operation(summary = "Delete a quiz by ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Quiz deleted successfully"),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    @ApiResponse(responseCode = "404", description = "Quiz not found", content = @Content),
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteQuiz(@PathVariable(name = "id") String id) {
    quizService.deleteQuizById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  @Operation(summary = "Submit answers for a quiz")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Quiz answers submitted successfully",
      content = {
        @Content(mediaType = "application/json",
          schema = @Schema(implementation = ResultDto.class))
      }),
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    @ApiResponse(responseCode = "404", description = "Quiz not found", content = @Content),
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PostMapping("/{id}/submit")
  public ResponseEntity<ResultDto> submitQuizAnswers(@Valid @RequestBody AnswerDto answerDto,
                                                     @PathVariable String id) {
    ResultDto resultDto = quizService.validateAnswers(answerDto, id);
    return new ResponseEntity<>(resultDto, HttpStatus.OK);
  }
}
