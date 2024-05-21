package org.example.quizservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main application class for the Quiz Service.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class QuizServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuizServiceApplication.class, args);
  }

}
