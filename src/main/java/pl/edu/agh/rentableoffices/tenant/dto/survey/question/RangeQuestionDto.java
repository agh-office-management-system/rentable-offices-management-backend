package pl.edu.agh.rentableoffices.tenant.dto.survey.question;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@JsonTypeName("RANGE")
@ApiModel(parent = QuestionDto.class, value = "Zasób reprezentujący pytanie \"w skali od a do b\"")
public class RangeQuestionDto extends QuestionDto {
    @ApiModelProperty("Wartość minimalna")
    private int min;
    @ApiModelProperty("Wartość minimalna")
    private int max;

    @Override
    public String getType() {
        return "RANGE";
    }

    public RangeQuestionDto(String code, String value, int min, int max) {
        this.code = code;
        this.value = value;
        this.min = min;
        this.max = max;
    }
}
