package com.example.restfulblogapplication.utils;

import lombok.experimental.UtilityClass;

/**
 * Utility class for holding application constants.
 */
@UtilityClass
public class ApplicationConstant {

  public static final String POST_NOT_FOUND_MESSAGE = "Post not found with id: ";
  public static final String QUIZ_NOT_FOUND_MESSAGE = "Quiz not found with id: ";
  public static final String COMMENT_NOT_FOUND_MESSAGE = "Comment not found with id: ";
  public static final String COMMENT_DOES_NOT_BELONG_MESSAGE = "Comment does not belong to post";
  public static final String EMAIL_ALREADY_TAKEN = "Email is already taken!";

  public static final String CONGRATULATIONS_MESSAGE = "Congratulations! All answers are correct!";
  public static final String TRY_AGAIN_MESSAGE = "Some answers are incorrect. Please review and try again.";
}
