package pl.edu.agh.rentableoffices.tenant.dto.survey.answer;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;

@JsonTypeName("RANGE")
@ApiModel(parent = AnswerDto.class)
public class RangeAnswerDto  extends AnswerDto<Integer> {
    public RangeAnswerDto(String code, Integer value) {
        this.code = code;
        this.value = value;
    }
}
