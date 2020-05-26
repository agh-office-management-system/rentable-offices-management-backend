package pl.edu.agh.rentableoffices.tenant.dto.survey;

import lombok.*;
import pl.edu.agh.rentableoffices.tenant.dto.survey.question.QuestionDto;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SurveyDto {
    private String name;
    private String description;

    private Set<QuestionDto> questions;
}
