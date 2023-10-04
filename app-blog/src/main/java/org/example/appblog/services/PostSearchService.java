package org.example.appblog.services;

import java.util.List;
import org.example.appblog.dtos.PostDto;

/**
 * Service interface for searching posts using full-text search with TSV.
 */
public interface PostSearchService {

  List<PostDto> searchByFullTextUsingTsv(String query, int pageNo, int pageSize);
}
