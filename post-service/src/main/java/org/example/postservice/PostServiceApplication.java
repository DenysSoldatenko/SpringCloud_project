package org.example.postservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main application class for the blog module.
 */
@EnableDiscoveryClient
@EnableJpaRepositories("org.example.postservice.repositories")
@EntityScan(value = "org.example.postservice.entities")
@SpringBootApplication
public class PostServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(PostServiceApplication.class, args);
  }

}
