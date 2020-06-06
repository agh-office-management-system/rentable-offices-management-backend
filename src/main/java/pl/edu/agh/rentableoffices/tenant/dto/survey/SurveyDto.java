package pl.edu.agh.rentableoffices.tenant.dto.survey;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import pl.edu.agh.rentableoffices.tenant.dto.survey.question.QuestionDto;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("Zasób reprezentujący ankietę")
public class SurveyDto {
    @ApiModelProperty("Nazwa ankiety")
    private String name;
    @ApiModelProperty("Opis ankiety")
    private String description;
    @ApiModelProperty("Zbiór pytań")
    private Set<QuestionDto> questions;
}
