package org.example.appblog.mappers;

import javax.annotation.processing.Generated;
import org.example.appblog.dtos.PostDto;
import org.example.appblog.entities.Post;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-07T15:31:46+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 20 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post toModel(PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( postDto.id() );
        post.setTitle( postDto.title() );
        post.setDescription( postDto.description() );
        post.setContent( postDto.content() );

        return post;
    }

    @Override
    public PostDto toDto(Post post) {
        if ( post == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String description = null;
        String content = null;

        id = post.getId();
        title = post.getTitle();
        description = post.getDescription();
        content = post.getContent();

        PostDto postDto = new PostDto( id, title, description, content );

        return postDto;
    }
}
