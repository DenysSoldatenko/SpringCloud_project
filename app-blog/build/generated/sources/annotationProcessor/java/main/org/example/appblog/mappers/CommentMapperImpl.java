package org.example.appblog.mappers;

import javax.annotation.processing.Generated;
import org.example.appblog.dtos.CommentDto;
import org.example.appblog.entities.Comment;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-07T15:31:46+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 20 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment toModel(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setId( commentDto.id() );
        comment.setName( commentDto.name() );
        comment.setEmail( commentDto.email() );
        comment.setBody( commentDto.body() );

        return comment;
    }

    @Override
    public CommentDto toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String email = null;
        String body = null;

        id = comment.getId();
        name = comment.getName();
        email = comment.getEmail();
        body = comment.getBody();

        CommentDto commentDto = new CommentDto( id, name, email, body );

        return commentDto;
    }
}
