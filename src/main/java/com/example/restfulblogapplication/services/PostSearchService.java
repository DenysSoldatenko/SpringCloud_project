package com.example.restfulblogapplication.services;

import com.example.restfulblogapplication.dtos.PostDto;
import java.util.List;

/**
 * Service interface for searching posts using full-text search with TSV.
 */
public interface PostSearchService {

  List<PostDto> searchByFullTextUsingTsv(String query, int pageNo, int pageSize);
}
