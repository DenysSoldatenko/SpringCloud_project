package com.example.restfulblogapplication.dtos;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing the result of a quiz.
 */
public record ResultDto(String message, List<String> correctAnswers,
                        List<String> incorrectAnswers, int totalQuestions,
                        int correctCount) {
}
