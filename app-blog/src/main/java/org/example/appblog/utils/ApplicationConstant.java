package org.example.appblog.utils;

import lombok.experimental.UtilityClass;

/**
 * Utility class for holding application constants.
 */
@UtilityClass
public class ApplicationConstant {

  public static final String POST_NOT_FOUND_MESSAGE = "Post not found with id: ";
  public static final String COMMENT_NOT_FOUND_MESSAGE = "Comment not found with id: ";
  public static final String COMMENT_DOES_NOT_BELONG_MESSAGE = "Comment does not belong to post";

  public static final String DATA_INITIALIZATION_SUCCESS_MESSAGE
      = "Data initialization completed successfully!";
}
