package org.example.appblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main application class for the blog module.
 */
@EnableDiscoveryClient
@ComponentScan(basePackages = {"org.example.appblog", "org.example.appcommon"})
@EnableJpaRepositories("org.example.appblog.repositories")
@EntityScan(value = "org.example.appblog.entities")
@SpringBootApplication
public class AppBlogApplication {

  public static void main(String[] args) {
    SpringApplication.run(AppBlogApplication.class, args);
  }

}
