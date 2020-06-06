package pl.edu.agh.rentableoffices.tenant.dto.survey.question;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;

@JsonTypeName("STRING")
@ApiModel(parent = QuestionDto.class, value = "Zasób reprezentujący pytanie tekstowe")
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
