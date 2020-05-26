package pl.edu.agh.rentableoffices.tenant.dto.survey.answer;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("BOOLEAN")
public class BooleanAnswerDto extends AnswerDto<Boolean> {

    public BooleanAnswerDto(String code, Boolean value) {
        this.code = code;
        this.value = value;
    }
}
