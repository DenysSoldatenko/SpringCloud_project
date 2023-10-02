package com.example.restfulblogapplication.configurations;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for Elasticsearch settings and beans.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.restfulblogapplication.repositories")
@ComponentScan(basePackages = { "com.example.restfulblogapplication.services" })
public class ElasticSearchConfiguration {
  @Bean
  public RestClient getRestClient() {
    return RestClient.builder(new HttpHost("localhost", 9200)).build();
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public ElasticsearchTransport getElasticsearchTransport() {
    return new RestClientTransport(getRestClient(), new JacksonJsonpMapper());
  }

  @Bean
  public ElasticsearchClient getElasticsearchClient() {
    return new ElasticsearchClient(getElasticsearchTransport());
  }
}