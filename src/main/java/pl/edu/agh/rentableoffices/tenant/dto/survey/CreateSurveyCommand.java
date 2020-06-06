package pl.edu.agh.rentableoffices.tenant.dto.survey;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import pl.edu.agh.rentableoffices.tenant.dto.survey.question.QuestionDto;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("Zasób reprezentujący utworzenie ankiety")
public class CreateSurveyCommand {
    @NotEmpty
    @ApiModelProperty("Nazwa ankiety")
    private String name;

    @NotEmpty
    @ApiModelProperty("Opis ankiety")
    private String description;

    @NotEmpty
    @ApiModelProperty("Zbiór pytań")
    private Set<QuestionDto> questions;

    @ApiModelProperty("Lista najemców, których dotyczy ankieta")
    private List<Long> tenantIds;
}
