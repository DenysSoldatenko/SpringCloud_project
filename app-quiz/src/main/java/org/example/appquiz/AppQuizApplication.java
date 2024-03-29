package org.example.appquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application class for the quiz module.
 */
@EnableDiscoveryClient
@ComponentScan(basePackages = {"org.example.appquiz", "org.example.appcommon"})
@EntityScan(value = "org.example.appquiz.entities")
@SpringBootApplication
public class AppQuizApplication {

  public static void main(String[] args) {
    SpringApplication.run(AppQuizApplication.class, args);
  }

}
