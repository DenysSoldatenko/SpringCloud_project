package org.example.postservice.services;

import java.util.List;
import org.example.postservice.dtos.PostDto;

/**
 * Service interface for searching posts using full-text search with TSV.
 */
public interface PostSearchService {

  List<PostDto> searchByFullTextUsingTsv(String query, int pageNo, int pageSize);
}
