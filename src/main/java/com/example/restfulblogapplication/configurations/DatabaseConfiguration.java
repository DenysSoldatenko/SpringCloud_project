package com.example.restfulblogapplication.configurations;

import com.example.restfulblogapplication.entities.Comment;
import com.example.restfulblogapplication.entities.Post;
import com.example.restfulblogapplication.repositories.PostRepository;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfiguration {
  private static final int NUMBER_OF_POSTS = 100;
  private static final int NUMBER_OF_COMMENTS_PER_POST = 3;


  private final PostRepository postRepository;

  @Bean
  public CommandLineRunner initData() {
    return args -> {
      Faker faker = new Faker(new Locale("en"));

      for (int i = 0; i < NUMBER_OF_POSTS; i++) {
        Post post = new Post();
        post.setTitle(faker.book().title());
        post.setDescription(faker.lorem().sentence());
        post.setContent(faker.lorem().sentence());

        List<Comment> comments = generateComments(post, faker);
        post.setComments(comments);

        postRepository.save(post);
      }
    };
  }

  private List<Comment> generateComments(Post post, Faker faker) {
    List<Comment> comments = new ArrayList<>();
    for (int i = 0; i < NUMBER_OF_COMMENTS_PER_POST; i++) {
      Comment comment = new Comment();
      comment.setName(faker.name().fullName());
      comment.setEmail(faker.internet().emailAddress());
      comment.setBody(faker.lebowski().quote());
      comment.setPost(post);
      comments.add(comment);
    }
    return comments;
  }
}
