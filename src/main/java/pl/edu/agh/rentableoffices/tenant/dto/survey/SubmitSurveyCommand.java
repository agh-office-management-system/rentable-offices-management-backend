package pl.edu.agh.rentableoffices.tenant.dto.survey;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.agh.rentableoffices.tenant.dto.survey.answer.AnswerDto;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("Zasób reprezentujący żądanie utworzenia odpowiedzi do ankiety")
public class SubmitSurveyCommand {
    @ApiModelProperty("Id najemcy")
    private Long tenantId;
    @ApiModelProperty("Odpowiedzi na pytania")
    private Set<AnswerDto> answers;
}
