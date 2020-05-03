package pl.edu.agh.rentableoffices.tenant.dto.survey;

import lombok.*;
import pl.edu.agh.rentableoffices.tenant.dto.survey.QuestionDto;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateSurveyCommand {
    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private Set<QuestionDto> questions;

    private List<Long> tenantIds;
}
