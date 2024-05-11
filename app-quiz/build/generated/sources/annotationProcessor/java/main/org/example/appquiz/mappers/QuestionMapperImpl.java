package org.example.appquiz.mappers;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.example.appquiz.dtos.QuestionDto;
import org.example.appquiz.entities.Question;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-07T15:47:56+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 20 (Oracle Corporation)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public QuestionDto toDto(Question question) {
        if ( question == null ) {
            return null;
        }

        String name = null;
        Map<String, String> options = null;
        String answer = null;

        name = question.getName();
        Map<String, String> map = question.getOptions();
        if ( map != null ) {
            options = new LinkedHashMap<String, String>( map );
        }
        answer = question.getAnswer();

        QuestionDto questionDto = new QuestionDto( name, options, answer );

        return questionDto;
    }

    @Override
    public Question toModel(QuestionDto questionDto) {
        if ( questionDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setName( questionDto.name() );
        Map<String, String> map = questionDto.options();
        if ( map != null ) {
            question.setOptions( new LinkedHashMap<String, String>( map ) );
        }
        question.setAnswer( questionDto.answer() );

        return question;
    }
}
