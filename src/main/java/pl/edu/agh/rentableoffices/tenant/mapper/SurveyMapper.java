package pl.edu.agh.rentableoffices.tenant.mapper;

import org.springframework.stereotype.Component;
import pl.edu.agh.rentableoffices.common.AbstractMapper;
import pl.edu.agh.rentableoffices.tenant.dto.survey.QuestionDto;
import pl.edu.agh.rentableoffices.tenant.dto.survey.SurveyDto;
import pl.edu.agh.rentableoffices.tenant.model.survey.Survey;

import java.util.stream.Collectors;

@Component
public class SurveyMapper implements AbstractMapper<Survey, SurveyDto> {

    @Override
    public SurveyDto toDto(Survey entity) {
        return SurveyDto.builder()
                .description(entity.getDescription())
                .name(entity.getName())
                .questions(entity.getQuestions()
                        .stream()
                        .map(q -> QuestionDto.builder()
                                .code(q.getCode())
                                .value(q.getValue())
                                .type(q.getType())
                                .build())
                        .collect(Collectors.toSet())
                )
                .build();
    }
}
