package pl.edu.agh.rentableoffices.tenant.dto.survey.answer;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;

@JsonTypeName("BOOLEAN")
@ApiModel(parent = AnswerDto.class)
public class BooleanAnswerDto extends AnswerDto<Boolean> {

    public BooleanAnswerDto(String code, Boolean value) {
        this.code = code;
        this.value = value;
    }
}
