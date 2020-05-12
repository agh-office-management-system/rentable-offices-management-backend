package pl.edu.agh.rentableoffices.tenant.dto.survey;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("STRING")
public class StringQuestionDto extends QuestionDto {

    @Override
    public String getType() {
        return "STRING";
    }

    public StringQuestionDto(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
