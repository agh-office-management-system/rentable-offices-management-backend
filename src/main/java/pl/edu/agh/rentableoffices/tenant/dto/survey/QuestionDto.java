package pl.edu.agh.rentableoffices.tenant.dto.survey;

import lombok.*;
import pl.edu.agh.rentableoffices.tenant.model.survey.QuestionType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionDto {
    private String code;

    private String value;

    private QuestionType type;
}
