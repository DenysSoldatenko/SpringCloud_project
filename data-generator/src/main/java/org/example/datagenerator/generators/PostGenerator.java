package org.example.datagenerator.generators;

import static java.util.stream.IntStream.range;

import java.util.List;
import java.util.Locale;
import net.datafaker.Faker;
import org.example.appblog.entities.Comment;
import org.example.appblog.entities.Post;
import org.springframework.stereotype.Component;

/**
 * Component for generating fake data for posts and comments.
 */
@Component
public class PostGenerator {

  private final Faker faker = new Faker(new Locale("en"));

  /**
   * Generates a batch of posts.
   *
   * @param batchSize The size of the batch to generate.
   * @return A list of posts.
   */
  public List<Post> generatePostBatch(int batchSize) {
    return range(0, batchSize)
        .mapToObj(i -> generatePost())
        .toList();
  }

  private Post generatePost() {
    Post post = new Post();
    post.setTitle(faker.book().title());
    post.setDescription(faker.lorem().sentence());
    post.setContent(faker.lorem().sentence(500));
    List<Comment> comments = generateComments(post);
    post.setComments(comments);
    return post;
  }

  private List<Comment> generateComments(Post post) {
    int numberOfComments = faker.random().nextInt(10) + 1;
    return range(0, numberOfComments)
      .mapToObj(i -> generateComment(post))
      .toList();
  }

  private Comment generateComment(Post post) {
    Comment comment = new Comment();
    comment.setName(faker.name().fullName());
    comment.setEmail(faker.internet().emailAddress());
    comment.setBody(faker.lebowski().quote());
    comment.setPost(post);
    return comment;
  }
}
