package pl.edu.agh.rentableoffices.tenant.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubmitSurveyCommand {
    private Long tenantId;
    private Set<AnswerDto> answers;
}
