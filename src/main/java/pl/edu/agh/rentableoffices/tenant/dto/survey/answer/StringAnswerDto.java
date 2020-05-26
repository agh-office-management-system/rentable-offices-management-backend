package pl.edu.agh.rentableoffices.tenant.dto.survey.answer;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("STRING")
public class StringAnswerDto extends AnswerDto<String> {
    public StringAnswerDto(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
