package org.example.datagenerator;

import org.example.appquiz.configurations.ElasticSearchConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Main application class for the data generator module.
 */
@ComponentScan(basePackages = {
  "org.example.appblog",
  "org.example.appquiz",
  "org.example.datagenerator"
})
@Import(ElasticSearchConfiguration.class)
@EntityScan({"org.example.appblog.entities", "org.example.appquiz.entities"})
@SpringBootApplication
public class DataGeneratorApplication {

  public static void main(String[] args) {
    SpringApplication.run(DataGeneratorApplication.class, args);
  }

}
