package org.example.appquiz.services.impl;

import static java.util.stream.IntStream.range;
import static org.example.appquiz.utils.ApplicationConstant.CONGRATULATIONS_MESSAGE;
import static org.example.appquiz.utils.ApplicationConstant.QUIZ_NOT_FOUND_MESSAGE;
import static org.example.appquiz.utils.ApplicationConstant.TRY_AGAIN_MESSAGE;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.appquiz.dtos.AnswerDto;
import org.example.appquiz.dtos.QuestionDto;
import org.example.appquiz.dtos.QuizDto;
import org.example.appquiz.dtos.ResultDto;
import org.example.appquiz.entities.Quiz;
import org.example.appquiz.exceptions.QuizNotFoundException;
import org.example.appquiz.mappers.QuestionMapper;
import org.example.appquiz.mappers.QuizMapper;
import org.example.appquiz.repositories.QuizRepository;
import org.example.appquiz.services.QuizService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link QuizService} interface.
 */
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

  private final QuizRepository quizRepository;
  private final QuizMapper quizMapper;
  private final QuestionMapper questionMapper;

  @Override
  public QuizDto createQuiz(QuizDto quizDto) {
    Quiz quiz = quizMapper.toModel(quizDto);
    quizRepository.save(quiz);
    return quizMapper.toDto(quiz);
  }

  @Override
  public List<QuizDto> getAllQuizPaginated(int pageNo, int pageSize,
                                           String sortBy, String sortDir) {
    if (!sortBy.endsWith(".keyword")) {
      sortBy += ".keyword";
    }
    Sort sort = Sort.by(sortDir.equals("asc") ? ASC : DESC, sortBy);
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    return quizRepository.findAll(pageable).stream().map(quizMapper::toDto).toList();
  }

  @Override
  public QuizDto getQuizById(String id) {
    Quiz quiz = quizRepository.findById(id)
        .orElseThrow(() -> new QuizNotFoundException(QUIZ_NOT_FOUND_MESSAGE + id));
    return quizMapper.toDto(quiz);
  }

  @Override
  public QuizDto updateQuizById(QuizDto quizDto, String id) {
    Quiz quiz = quizRepository.findById(id)
        .orElseThrow(() -> new QuizNotFoundException(QUIZ_NOT_FOUND_MESSAGE + id));

    quiz.setName(quizDto.name());
    quiz.setCategory(quizDto.category());
    quiz.setDifficulty(quizDto.difficulty());
    quiz.setQuestions(quizDto.questions().stream().map(questionMapper::toModel).toList());

    Quiz updatedQuiz = quizRepository.save(quiz);
    return quizMapper.toDto(updatedQuiz);
  }

  @Override
  public void deleteQuizById(String id) {
    Quiz quiz = quizRepository.findById(id)
        .orElseThrow(() -> new QuizNotFoundException(QUIZ_NOT_FOUND_MESSAGE + id));
    quizRepository.delete(quiz);
  }

  @Override
  public ResultDto validateAnswers(AnswerDto answerDto, String id) {
    Quiz quiz = quizRepository.findById(id)
        .orElseThrow(() -> new QuizNotFoundException(QUIZ_NOT_FOUND_MESSAGE + id));

    List<QuestionDto> quizQuestions = quiz.getQuestions().stream()
        .map(questionMapper::toDto)
        .toList();

    List<String> correctAnswers = new ArrayList<>();
    List<String> incorrectAnswers = new ArrayList<>();

    range(0, answerDto.userAnswers().size())
        .forEach(i -> {
          String userAnswer = answerDto.userAnswers().get(i);
          QuestionDto correctAnswer = quizQuestions.get(i);

          (userAnswer.equals(correctAnswer.answer()) ? correctAnswers : incorrectAnswers)
            .add("Question " + (i + 1) + ": " + userAnswer);
        });

    int totalQuestions = answerDto.userAnswers().size();
    int correctCount = correctAnswers.size();
    String message = correctCount == totalQuestions ? CONGRATULATIONS_MESSAGE : TRY_AGAIN_MESSAGE;

    return new ResultDto(message, correctAnswers, incorrectAnswers, totalQuestions, correctCount);
  }
}
